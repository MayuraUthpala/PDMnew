package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class Fueldul extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fueldul);
        CardView fue = findViewById(R.id.f1);
        CardView plu = findViewById(R.id.mm);
        CardView rr = findViewById(R.id.rid);
        CardView gal = findViewById(R.id.gid);
        fue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fueldul.this,Fuel.class);
                startActivity(i);
            }


        });
        plu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(Fueldul.this,Maintain.class);
                startActivity(j);
            }


        });

        rr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent re1 = new Intent(Fueldul.this,Reminder.class);
                startActivity(re1);
            }
        });

        gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g = new Intent(Fueldul.this, UserGallery.class);
                startActivity(g);
            }
        });



    }
}
