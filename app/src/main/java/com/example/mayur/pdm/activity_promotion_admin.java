package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_promotion_admin extends AppCompatActivity {

    private Button Add;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_admin);

        mToolbar=(Toolbar) findViewById(R.id.prom_admin);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Promotions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Promotion");


        Add = (Button) findViewById(R.id.btn_submitPromotion);
        final EditText promtext = (EditText) findViewById(R.id.promotionadd);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.push().setValue(promtext.getText().toString());
                Toast.makeText(getApplicationContext(),"Saved to database",Toast.LENGTH_SHORT).show();
                promtext.setText("");


                       /* new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(getApplicationContext(),"Saved to database",Toast.LENGTH_SHORT).show();
                    }
                };*/





            }
        });
    }
}
