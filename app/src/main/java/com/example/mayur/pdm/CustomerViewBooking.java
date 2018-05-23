package com.example.mayur.pdm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CustomerViewBooking extends AppCompatActivity {

    TextView servicetxt;
    TextView pricetxt;
    TextView datetxt;
    TextView timetxt;
    TextView regnotxt;
    TextView modeltxt;
    Button cancelbook;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String cid,key;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_booking);

        String servtype=getIntent().getStringExtra("service");
        final String scgarge=getIntent().getStringExtra("price");
        String dates=getIntent().getStringExtra("date");
        String tslot=getIntent().getStringExtra("timeslot");
        String regino=getIntent().getStringExtra("regnum");
        String make=getIntent().getStringExtra("model");

        servicetxt=(TextView)findViewById(R.id.servicetxt);
        pricetxt=(TextView)findViewById(R.id.pricetxt);
        datetxt=(TextView)findViewById(R.id.datetxt);
        timetxt=(TextView)findViewById(R.id.timetxt);
        regnotxt=(TextView)findViewById(R.id.regnotxt);
        modeltxt=(TextView)findViewById(R.id.modeltxt);
        cancelbook=(Button)findViewById(R.id.cancelbook);

        servicetxt.setText(servtype);
        pricetxt.setText(scgarge);
        datetxt.setText(dates);
        timetxt.setText(tslot);
        regnotxt.setText(regino);
        modeltxt.setText(make);

        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();

        }

        FirebaseUser user=firebaseAuth.getCurrentUser();

        cid = user.getUid();

        final Query query= FirebaseDatabase.getInstance().getReference().child("Bookings").orderByChild("UserId")
                .equalTo(cid).limitToLast(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    key=ds.getKey();
                    databaseReference=FirebaseDatabase.getInstance().getReference().child("Bookings").child(key);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cancelbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double charge=Double.parseDouble(scgarge);
                charge=charge-(charge*5/100);
                String newcharge=String.valueOf(charge);
                HashMap userMap = new HashMap();
                userMap.put("UserId",cid);
                userMap.put("SecrviceType","");
                userMap.put("ServiceCharge",newcharge);
                userMap.put("Date","");
                userMap.put("TimeSlot", "");
                userMap.put("VehicleNo","");
                userMap.put("Model","");
                //userMap.put()
                //userMap.put("feedback", feed);
               // userMap.put("rating", num);
                databaseReference.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Note that you have been charged 5% of your payment for cancelling booking.Rest have been transfrred to your acount", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Error occurred while updating data!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
