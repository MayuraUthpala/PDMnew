package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class chooseService extends AppCompatActivity {

    private Button service_section;
    private Button tuneup_section;
    private Button runrepbtn;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_service);

        service_section= (Button) findViewById(R.id.service_section);
        service_section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
              //  ref.push().child("Section").setValue("Service_Section");
                OpenSeviceSec();
            }
        });


        tuneup_section= (Button) findViewById(R.id.tuneup_section);
        tuneup_section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
               // ref.push().child("Section").setValue("Tuneup_Section");
                openTuneupSec();
            }
        });


        runrepbtn= (Button) findViewById(R.id.runrepbtn);
        runrepbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
              //  ref.push().child("Section").setValue("RunningRep_Section");
                OpenRunRepSec();
            }
        });

    }

    public void OpenSeviceSec() {
        Intent intent = new Intent(this, Service_sec_opt.class);
        startActivity(intent);
    }

    public void openTuneupSec(){
        Intent intent = new Intent(this, Tuneup_sec_opt.class);
        startActivity(intent);
    }

    public void OpenRunRepSec(){
        Intent intent = new Intent(this, RunningRep_sec_opt.class);
        startActivity(intent);
    }

    }
