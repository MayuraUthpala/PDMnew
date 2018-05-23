package com.example.mayur.pdm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Map;

public class bookingServiceU extends AppCompatActivity {

    Button selectdate;
    Button proceedbtn;
    TextView date;
    TextView getuname;
    TextView getprc;
    Spinner spinner;
    EditText editText1;
    EditText editText2;
    String times[]={"8.00-9.00","8.30-9.30","9.00-10.00","9.30-10.30","10.00-11.00","10.30-11.30","11.00-12.00","11.30-12.30","2.00-3.00","2.30-3.30","3.00-4.00"};
    ArrayAdapter<String>arrayAdapter;
    private DatabaseReference ref;
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_service_u);

        mtoolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tbrServiceU);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("FAQs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mtoolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        getprc=(TextView)findViewById(R.id.getprc);

        ref = FirebaseDatabase.getInstance().getReference().child("services");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();

                    Object nn = map.get("ServiceName");
                    String sn = getuname.getText().toString();

                    if (nn.equals(sn)) {
                        Object mm = map.get("ServicePrice");
                        String kk = String.valueOf(mm);
                        //Log.d("price", String.valueOf(kk));
                        getprc.setText(kk);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        spinner=(Spinner)findViewById(R.id.spinner);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,times);
        spinner.setAdapter(arrayAdapter);

        selectdate = findViewById(R.id.btnDate);
        date = findViewById(R.id.getdate);

        getuname=findViewById(R.id.getuname);
        getuname.setText(getIntent().getStringExtra("ServiceName"));

        proceedbtn=findViewById(R.id.proceedbtn);

        proceedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText1=(EditText)findViewById(R.id.regnum);
                String regino=editText1.getText().toString();
                if (TextUtils.isEmpty(regino)) {
                    Toast.makeText(getApplicationContext(), "Enter Vehicle Registration Number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                editText2=(EditText) findViewById(R.id.getmk);
                String make=editText2.getText().toString();
                if (TextUtils.isEmpty(make)) {
                    Toast.makeText(getApplicationContext(), "Enter Vehicle Model!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String dt=date.getText().toString();
                if(TextUtils.isEmpty(dt)){
                    Toast.makeText(getApplicationContext(), "Select Date!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String servicetp=getuname.getText().toString();
                String servprice=getprc.getText().toString();
                String datet=date.getText().toString();
                String time=spinner.getSelectedItem().toString();;
                String regno=editText1.getText().toString();
                String model=editText2.getText().toString();

                Intent intent=new Intent(bookingServiceU.this,PaypalORCredit.class);
                intent.putExtra("service",servicetp);
                intent.putExtra("price",servprice);
                intent.putExtra("date",datet);
                intent.putExtra("timeslot",time);
                intent.putExtra("regnum",regno);
                intent.putExtra("model",model);
                startActivity(intent);

/*                Intent intent1=new Intent(bookingServiceU.this,PayPal.class);
                intent1.putExtra("service",servicetp);
                intent1.putExtra("price",servprice);
                intent1.putExtra("date",datet);
                intent1.putExtra("timeslot",time);
                intent1.putExtra("regnum",regno);
                intent1.putExtra("model",model);
                startActivity(intent1);*/


            }
        });

        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(bookingServiceU.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + month + "/" + year);
                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();
            }

        });

    }
}
