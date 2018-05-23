package com.example.mayur.pdm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class change_fname extends AppCompatActivity {

    private EditText FirstName;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private Toolbar mToolbar;
    private Button change;
    String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_fname);

        mToolbar=(Toolbar) findViewById(R.id.fname_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("User First Name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        FirstName = (EditText) findViewById(R.id.changeFName);
        change = (Button) findViewById(R.id.edit_fname);

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

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = FirstName.getText().toString().trim();

                if (TextUtils.isEmpty(fname)) {
                    Toast.makeText(change_fname.this, "Enter first name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap userMap = new HashMap();
                userMap.put("fname", fname);
                databaseReference.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Saved successfully!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(change_fname.this, MainActivity.class));
                            finish();
                        } else {
                            String message = task.getException().getMessage();
                            Toast.makeText(change_fname.this, "Error occurred!" + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}
