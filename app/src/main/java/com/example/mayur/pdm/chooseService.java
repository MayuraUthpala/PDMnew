package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class chooseService extends AppCompatActivity {

    private Button service_section;
    private Button tuneup_section;
    private Button runrepbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_service);

        service_section= (Button) findViewById(R.id.service_section);
        service_section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenSeviceSec();
            }
        });

        tuneup_section= (Button) findViewById(R.id.tuneup_section);
        tuneup_section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTuneupSec();
            }
        });

        runrepbtn= (Button) findViewById(R.id.runrepbtn);
        runrepbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenRunRepSec();
            }
        });
    }

    public void OpenSeviceSec() {
        Intent intent = new Intent(this, Service_sec_opt.class);
        startActivity(intent);
    }

    public void openTuneupSec(){
        Intent intent = new Intent(this, Tuneup_sec_opt.class);
        startActivity(intent);
    }

    public void OpenRunRepSec(){
        Intent intent = new Intent(this, RunningRep_sec_opt.class);
        startActivity(intent);
    }
}
