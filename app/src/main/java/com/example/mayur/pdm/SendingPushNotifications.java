package com.example.mayur.pdm;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SendingPushNotifications extends AppCompatActivity {

    private Button btn;
    private Button ofrslst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_push_notifications);

        btn=(Button)findViewById(R.id.notify);


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String url = "https://console.firebase.google.com/u/0/project/loginapplication-43a51/notification";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        ofrslst=(Button)findViewById(R.id.offerslist);
        ofrslst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openoffers();
            }
        });

       // btn.setOnClickListener();
    }

    public void open(View view){
        Intent browserintent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://console.firebase.google.com/u/0/project/loginapplication-43a51/notification"));
        startActivity(browserintent);
    }

    public void openoffers(){
        Intent intent = new Intent(this, Offers.class);
        startActivity(intent);
    }

}
