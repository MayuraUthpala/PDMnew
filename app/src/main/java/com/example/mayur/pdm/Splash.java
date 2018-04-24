package com.example.mayur.pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
private ImageView tv;
//private TextView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tv = (ImageView)findViewById(R.id.tv);
        //iv = (TextView)findViewById(R.id.iv);
        Animation myanin = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.startAnimation(myanin);
       // iv.startAnimation(myanin);
        final Intent i = new Intent(this,MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){e.printStackTrace();}
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
            timer.start();
    }
}
