package com.example.mayur.pdm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class Book_Today extends AppCompatActivity {

    Spinner spinner_d;
    Spinner spinner_e;
    String names[]={"Full Service","Oil Change","Cut N Polish","Wheel Allignment","General Repair","Tune up","Engine Scan","Electrical Repair"};
    String times[]={"8.00-9.00","8.30-9.30","9.00-10.00","9.30-10.30","10.00-11.00","10.30-11.30","11.00-12.00","11.30-12.30","2.00-3.00","2.30-3.30","3.00-4.00"};
    ArrayAdapter<String>arrayAdapter;

    private Button cont;
    private Button add;
    private Button del;
    private Button update;
    Button selectDate;
    TextView date;
    EditText regno;
    EditText modelname;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DatePicker datePicker;

    private FirebaseAuth auth;
    String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__today);

        spinner_d=(Spinner)findViewById(R.id.spinner);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        spinner_d.setAdapter(arrayAdapter);

        spinner_e=(Spinner)findViewById(R.id.spinner2);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,times);
        spinner_e.setAdapter(arrayAdapter);
        cont=(Button)findViewById(R.id.cont);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regno=(EditText)findViewById(R.id.regnum);
                String regino=regno.getText().toString();
                if (TextUtils.isEmpty(regino)) {
                    Toast.makeText(getApplicationContext(), "Enter Vehicle Registration Number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                modelname=(EditText) findViewById(R.id.model);
                String make=modelname.getText().toString();
                if (TextUtils.isEmpty(make)) {
                    Toast.makeText(getApplicationContext(), "Enter Vehicle Model!", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String servtype=spinner_d.getSelectedItem().toString();
                final String tslot=spinner_e.getSelectedItem().toString();
                final String dates=date.getText().toString();
                auth = FirebaseAuth.getInstance();
                cid=auth.getCurrentUser().getUid();
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
                HashMap userMap = new HashMap();
                userMap.put("UserId",cid);
                userMap.put("SecrviceType",servtype);
                userMap.put("Date",dates);
                userMap.put("TimeSlot", tslot);
                userMap.put("VehicleNo",regino);
                userMap.put("Model",make);
                ref.push().setValue(userMap);

                OpenCusInfo();
            }
        });

        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Book_Today.this,
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

    public void OpenCusInfo(){

        Intent intent = new Intent(this, Payment_gateway.class);
        startActivity(intent);

    }
}
