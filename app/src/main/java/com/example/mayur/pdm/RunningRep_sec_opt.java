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

public class RunningRep_sec_opt extends AppCompatActivity {

    private Button btn10;
    private Button button9;
    private Button button8;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_rep_sec_opt);

        btn10= (Button) findViewById(R.id.btn10);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
                HashMap userMap = new HashMap();
                userMap.put("Section", "Running_Repair");
                userMap.put("Type", "Wheel_alignment");
                ref.push().setValue(userMap);

                OpenBookDateTime();
            }
        });

        button8= (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
                HashMap userMap = new HashMap();
                userMap.put("Section", "Running_Repair");
                userMap.put("Type", "Gereral_Repair");
                ref.push().setValue(userMap);

                OpenBookDateTime();
            }
        });


        button9= (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
                HashMap userMap = new HashMap();
                userMap.put("Section", "Running_Repair");
                userMap.put("Type", "Fluid_transmission");
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
