package com.example.mayur.pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class UIforBooking extends AppCompatActivity {

    private Button button;
    private Button button2;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String cid,key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uifor_booking);

        button=(Button)findViewById(R.id.bknw);
        button2=(Button)findViewById(R.id.clnw);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenSelectServices();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCancel();
            }
        });
    }

    public void OpenSelectServices(){
        Intent intent = new Intent(this, SelectServiceU.class);
        startActivity(intent);
    }

    public void openCancel(){
        Intent intent=new Intent(this,CustomerViewBooking.class);
        startActivity(intent);
    }
}
