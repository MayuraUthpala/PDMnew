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

public class Tuneup_sec_opt extends AppCompatActivity {

    private Button button;
    private Button button6;
    private  Button button7;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuneup_sec_opt);

        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
                HashMap userMap = new HashMap();
                userMap.put("Section", "Tuneup_Section");
                userMap.put("Type", "TuneUp");
                ref.push().setValue(userMap);

                OpenBookDateTime();
            }
        });

        button6= (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
                HashMap userMap = new HashMap();
                userMap.put("Section", "Tuneup_Section");
                userMap.put("Type", "EngineScan");
                ref.push().setValue(userMap);

                OpenBookDateTime();
            }
        });


        button7= (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("booking");
                HashMap userMap = new HashMap();
                userMap.put("Section", "Tuneup_Section");
                userMap.put("Type", "Electrical_Repair");
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
