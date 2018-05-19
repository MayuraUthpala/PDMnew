package com.example.mayur.pdm;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Payment_gateway extends AppCompatActivity {

    Spinner spinner_d;
    String method[]={"debitCard","creditCard"};
    ArrayAdapter<String> arrayAdapter;
    Button proceed;
    EditText editText;
    TextView getcharge;
    TextView FirstName;
    private FirebaseAuth auth;
    private FirebaseUser mCurrentUser;
    String cid;
    private DatabaseReference databaseReference;
    //private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);

        spinner_d=(Spinner)findViewById(R.id.spinner4);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,method);
        spinner_d.setAdapter(arrayAdapter);

        String paymethod=spinner_d.getSelectedItem().toString();

        getcharge=(TextView)findViewById(R.id.getcharge);
        getcharge.setText(getIntent().getStringExtra("price"));
        proceed=(Button)findViewById(R.id.proceed);
        editText=(EditText)findViewById(R.id.editText);
        FirstName=(TextView) findViewById(R.id.idd);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String num = editText.getText().toString().trim();
                int numT = 0;
                if (num.isEmpty()) {
                    Toast.makeText(Payment_gateway.this, "Enter Card Number!", Toast.LENGTH_SHORT).show();
                }
                if (num.length() !=16) {
                    Toast.makeText(getApplicationContext(), "Incorrect card number length!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String servtype=getIntent().getStringExtra("service");
                String scgarge=getIntent().getStringExtra("price");
                String dates=getIntent().getStringExtra("date");
                String tslot=getIntent().getStringExtra("timeslot");
                String regino=getIntent().getStringExtra("regnum");
                String make=getIntent().getStringExtra("model");
                final String nm=FirstName.getText().toString();

                auth = FirebaseAuth.getInstance();
                cid=auth.getCurrentUser().getUid();

                databaseReference= FirebaseDatabase.getInstance().getReference().child("users").child(cid);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            String vfname=dataSnapshot.child("fname").getValue().toString();
                            FirstName.setText(vfname);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Bookings");
                HashMap userMap = new HashMap();
                userMap.put("UserId",cid);
                //
                // userMap.put("UserName",nm);
                userMap.put("SecrviceType",servtype);
                userMap.put("ServiceCharge",scgarge);
                userMap.put("Date",dates);
                userMap.put("TimeSlot", tslot);
                userMap.put("VehicleNo",regino);
                userMap.put("Model",make);
                ref.push().setValue(userMap);

                Toast.makeText(Payment_gateway.this, "Successfully Booked!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
