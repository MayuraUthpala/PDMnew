package com.example.mayur.pdm;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Payment_gateway extends AppCompatActivity {

    Spinner spinner_d;
    String method[]={"visa_card","master_card"};
    ArrayAdapter<String> arrayAdapter;
    Spinner spinner_e;
    String advamt[]={"500.00","1000.00"};
    Button proceed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);

        spinner_d=(Spinner)findViewById(R.id.spinner4);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,method);
        spinner_d.setAdapter(arrayAdapter);

        spinner_e=(Spinner)findViewById(R.id.spinner5);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,advamt);
        spinner_e.setAdapter(arrayAdapter);

        String paymethod=spinner_d.getSelectedItem().toString();
        String advaceamnt=spinner_e.getSelectedItem().toString();

        proceed=(Button)findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Payment_gateway.this, "Payment Successful!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
