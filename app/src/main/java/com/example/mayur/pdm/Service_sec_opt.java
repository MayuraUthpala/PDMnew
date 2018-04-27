package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Service_sec_opt extends AppCompatActivity {

    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_sec_opt);

        button2= (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
                HashMap userMap = new HashMap();
                userMap.put("Section", "Service");
                userMap.put("Type", "Full_Service");
                ref.push().setValue(userMap);

                OpenBookDateTime();
            }
        });

        button3= (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
                HashMap userMap = new HashMap();
                userMap.put("Section", "Service");
                userMap.put("Type", "Oil_Change");
                ref.push().setValue(userMap);

                OpenBookDateTime();
            }
        });


        button4= (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
                HashMap userMap = new HashMap();
                userMap.put("Section", "Service");
                userMap.put("Type", "Cut_N_Polish");
                ref.push().setValue(userMap);

                OpenBookDateTime();
            }
        });



        button5= (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
                HashMap userMap = new HashMap();
                userMap.put("Section", "Service");
                userMap.put("Type", "Interior_Clean");
                ref.push().setValue(userMap);

                OpenBookDateTime();
            }
        });

    }

    public void OpenBookDateTime() {
        Intent intent = new Intent(this, BookDateTime.class);
        startActivity(intent);
    }

    }
