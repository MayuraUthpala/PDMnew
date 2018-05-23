package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class EmergencyServices extends AppCompatActivity implements View.OnClickListener{
    private CardView help,map,chat;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_services);

        mToolbar = (Toolbar) findViewById(R.id.tbr_emer_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Emergency Service");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        //define cards of emergency
        help = (CardView) findViewById(R.id.ems_help);
        map = (CardView) findViewById(R.id.ems_map);
        chat = (CardView) findViewById(R.id.ems_chat);

        //add click listner to the cards
        help.setOnClickListener(this);
        map.setOnClickListener(this);
        chat.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        Intent i ;

        switch(v.getId()){
            case R.id.ems_help : i = new Intent(this, EmHelp.class);startActivity(i);break;
            case R.id.ems_map : i = new Intent(this, EmMap.class);startActivity(i);break;
            case R.id.ems_chat : i = new Intent(this, EmChat.class);startActivity(i);break;
            default:break;
        }
    }
}
