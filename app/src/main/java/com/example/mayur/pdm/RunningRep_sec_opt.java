package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RunningRep_sec_opt extends AppCompatActivity {

    private Button btn10;
    private Button button9;
    private Button button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_rep_sec_opt);

        btn10= (Button) findViewById(R.id.btn10);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenBookDateTime();
            }
        });

        button8= (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenBookDateTime();
            }
        });

        button9= (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenBookDateTime();
            }
        });
    }

    public void OpenBookDateTime() {
        Intent intent = new Intent(this, BookDateTime.class);
        startActivity(intent);
    }
}
