package com.example.mayur.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Map;

public class Reminder extends AppCompatActivity {

    private DatabaseReference ref;
     String currentDat;


    private TextView re1,re2,re3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Calendar calendar = Calendar.getInstance();
        currentDat = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime()).toString();



        re1 = findViewById(R.id.Re1);
        re2 = findViewById(R.id.Re2);
        re3 = findViewById(R.id.Re3);

        ref = FirebaseDatabase.getInstance().getReference().child("Maintain");


       ref.addValueEventListener(new ValueEventListener() {
            @Override


            //database su

            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dd : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) dd.getValue();
                    Object mil = map.get("Next_Check");

                    String pmil = String.valueOf(mil);

                    String par[] = currentDat.split("/");
                    String dat = "20"+par[2];

                        if (pmil.equals(par[0]+"/"+par[1]+"/"+dat)) {
                            Object se = map.get("Service");
                            String pse = String.valueOf(se);
                            re1.setText("Today you have to check ");
                            re2.setText(pse);
                            re3.setText(pmil);
                        }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }
}


