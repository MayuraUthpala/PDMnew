package com.example.mayur.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ServiceAdding extends AppCompatActivity {

    EditText service;
    EditText price;
    Button addbtn;
    FirebaseDatabase database;
    DatabaseReference ref;
    ServicesAuto serv;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_adding);

        service=(EditText)findViewById(R.id.serviceName);
        price=(EditText)findViewById(R.id.servicePrice);
        addbtn=(Button)findViewById(R.id.addbutton);
        //database = FirebaseDatabase.getInstance();
       // ref = database.getReference("Services");
        serv=new ServicesAuto();

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service=(EditText)findViewById(R.id.serviceName);
                String serv=service.getText().toString();
                if (TextUtils.isEmpty(serv)) {
                    Toast.makeText(getApplicationContext(), "Enter Service!", Toast.LENGTH_SHORT).show();
                    return;
                }
                price=(EditText) findViewById(R.id.servicePrice);
                String prc=price.getText().toString();
                if (TextUtils.isEmpty(prc)) {
                    Toast.makeText(getApplicationContext(), "Enter Service Price!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth = FirebaseAuth.getInstance();
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("services");
                HashMap userMap = new HashMap();
                userMap.put("ServiceName",serv);
                userMap.put("ServicePrice",prc);
                ref.push().setValue(userMap);
                Toast.makeText(getApplicationContext(), "Successfully added!", Toast.LENGTH_SHORT).show();
            }
        });

    }
/*    private void getValues()
    {
        serv.setServiceName(service.getText().toString());
        serv.setServicePrice(price.getText().toString());
    }*/



/*    public void AddsalonButtonclicked(View view) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String id=ref.push().getKey();
                getValues();
                ref.child(id).setValue(serv);
                Toast.makeText(ServiceAdding.this,"Data Inserted..",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
}
