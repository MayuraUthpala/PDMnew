package com.example.mayur.pdm;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class EmHelp extends AppCompatActivity implements View.OnClickListener {

    private static final int MY_REQUEST_INT = 1;
    private static Button b1;
    private static Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_em_help);
        b1 = (Button) findViewById(R.id.call1);
        b1.setOnClickListener(this);

        b2 = (Button) findViewById(R.id.call2);
        b2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
       switch(v.getId()){
           case R.id.call1:
               Intent callIntent = new Intent(Intent.ACTION_CALL);
               callIntent.setData(Uri.parse("tel:119"));
               if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                   // TODO: Consider calling
                   if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                       requestPermissions(new  String[]{Manifest.permission.CALL_PHONE},MY_REQUEST_INT);
                   }
                   return;
               }else {
                   startActivity(callIntent);}

                   break;


           case R.id.call2:
               Intent callIntent1 = new Intent(Intent.ACTION_CALL);
               callIntent1.setData(Uri.parse("tel:0715936704"));
               if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                   // TODO: Consider calling
                   if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                       requestPermissions(new  String[]{Manifest.permission.CALL_PHONE},MY_REQUEST_INT);
                   }
                   return;
               }else {
                   startActivity(callIntent1);}

                   break;

       }

        }

}
