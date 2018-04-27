package com.example.mayur.pdm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class BookDateTime extends AppCompatActivity {

    Spinner spinner_d;
    String names[]={"8.00-9.00","8.30-9.30","9.00-10.00","9.30-10.30","10.00-11.00","10.30-11.30","11.00-12.00","11.30-12.30","2.00-3.00","2.30-3.30","3.00-4.00"};
    ArrayAdapter<String>arrayAdapter;
    private Button button11;
   // private Button book;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_date_time);

        button11= (Button) findViewById(R.id.button11);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("booking");
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenCusInfo();
            }
        });

        spinner_d=(Spinner)findViewById(R.id.spinner_dropdown);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        spinner_d.setAdapter(arrayAdapter);

        spinner_d.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Selected time slot is:"+names[i],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    public void OpenCusInfo(){

        HashMap userMap = new HashMap();
        userMap.put("test", "temporary");
        databaseReference.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Saved successfully!", Toast.LENGTH_LONG).show();

                } else {
                    String message = task.getException().getMessage();
                    Toast.makeText(BookDateTime.this, "Error occurred!" + message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*Intent intent = new Intent(this, Customer_info.class);
        startActivity(intent);*/
    }
}
