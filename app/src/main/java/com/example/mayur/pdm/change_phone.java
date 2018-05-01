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

public class change_phone extends AppCompatActivity {

    private EditText Phone;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private Toolbar mToolbar;
    private Button change;
    String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);

        mToolbar=(Toolbar) findViewById(R.id.phone_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("User Phone Number");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Phone = (EditText) findViewById(R.id.changePhone);
        change = (Button) findViewById(R.id.edit_phone);

        auth = FirebaseAuth.getInstance();
        cid=auth.getCurrentUser().getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users").child(cid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String vphone=dataSnapshot.child("phone").getValue().toString();
                    Phone.setText(vphone);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = Phone.getText().toString().trim();

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(change_phone.this, "Enter Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int numT = 0;
                if (phone.length() !=9 ) {
                    Toast.makeText(getApplicationContext(), "Incorrect Phone number length!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!phone.isEmpty()){
                    try {
                        numT = Integer.parseInt(phone);
                        HashMap userMap = new HashMap();
                        userMap.put("phone", numT);
                        databaseReference.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Saved successfully!", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(change_phone.this, MainActivity.class));
                                    finish();
                                } else {
                                    String message = task.getException().getMessage();
                                    Toast.makeText(change_phone.this, "Error occurred!" + message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (NumberFormatException e) {
                        Toast.makeText(change_phone.this, "Enter Phone number!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
