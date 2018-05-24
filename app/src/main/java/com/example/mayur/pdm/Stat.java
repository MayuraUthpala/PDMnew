package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Stat extends AppCompatActivity {


    private TextView mdis;
    private TextView mcs;
    private TextView fu;
    private TextView m;
    private Button gene;
    private TextView gene1;

    private EditText yr;
    String t1,t2,t3,t4;


    private DatabaseReference mdata;
    String record="";
    ArrayAdapter<String> adapter;

    String months[] = {"Month","January","February","March","April","May","June","July","Augest","September","October","November","December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        mdis = findViewById(R.id.id_emil);
        mcs = findViewById(R.id.id_ecs);
        fu = findViewById(R.id.id_efu);
        m = findViewById(R.id.id_eemi);

        gene = findViewById(R.id.gen);
        gene1 = findViewById(R.id.gen1);

        yr = findViewById(R.id.id_eyear);

        t1 = mdis.getText().toString();
        t2 = mcs.getText().toString();
        t3 = fu.getText().toString();
        t4 = m.getText().toString();

        gene1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Mileage = mdis.getText().toString();
                String fuel = fu.getText().toString();
                String total = mcs.getText().toString();
                String co2 = m.getText().toString();

                Intent in = new Intent(Stat.this,Bar.class);
                in.putExtra("Mileage",Mileage);
                in.putExtra("Fuel Consumption",fuel);
                in.putExtra("Total Cost",total);
                in.putExtra("CO2 Emission",co2);
                startActivity(in);
            }
        });

        gene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yy = yr.getText().toString();

                if (record == "0" || TextUtils.isEmpty(yy)) {
                    Toast.makeText(Stat.this, "Both Month and year should be added", Toast.LENGTH_LONG).show();
                    return;
                } else {

                    mdata = FirebaseDatabase.getInstance().getReference().child("Fuel");
                    mdata.addValueEventListener(new ValueEventListener() {
                        @Override


                        //database su

                        public void onDataChange(DataSnapshot dataSnapshot) {
                            float sum = 0.0f;
                            float sum1 = 0.0f;
                            float sum2 = 0.0f;
                            float sum3 = 0.0f;

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                Object mil = map.get("Date");
                                String pmil = String.valueOf(mil);
                                String part1[] = pmil.split("/");

                                String year = yr.getText().toString();

                                if (part1[0].equals(record) && part1[2].equals(year)) {
                                    Object mileage = map.get("Mileage");
                                    String s1 = String.valueOf(mileage);
                                    float ff = new Float(s1).floatValue();
                                    //  Double pmileage = Double.parseDouble(String.valueOf(mileage));


                                    sum += ff;


                                    Log.d("Sum", String.valueOf(sum));
                                    mdis.setText(String.valueOf(sum));
                                }

                            }

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Map<String, Object> map = (Map<String, Object>) ds.getValue();

                                Object mil1 = map.get("Date");
                                String pmil1 = String.valueOf(mil1);
                                String part1[] = pmil1.split("/");

                                String year = yr.getText().toString();

                                if (part1[0].equals(record) && part1[2].equals(year)) {
                                    Object mileage1 = map.get("Quantity");
                                    String s2 = String.valueOf(mileage1);
                                    float ff1 = new Float(s2).floatValue();
                                    // Double pmileage1 = Double.parseDouble(String.valueOf(mileage1));


                                    sum1 += ff1;

                                    Log.d("Sum", String.valueOf(sum1));
                                    fu.setText(String.valueOf(sum1));
                                }


                            }
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Map<String, Object> map = (Map<String, Object>) ds.getValue();

                                Object mil2 = map.get("Date");
                                String pmil2 = String.valueOf(mil2);
                                String part1[] = pmil2.split("/");

                                String year = yr.getText().toString();

                                if (part1[0].equals(record) && part1[2].equals(year)) {

                                    Object mileage2 = map.get("Cost");
                                    String s3 = String.valueOf(mileage2);
                                    float ff2 = new Float(s3).floatValue();
                                    // Double pmileage2= Double.parseDouble(String.valueOf(mileage2));

                                    sum2 += ff2;

                                    Log.d("Sum", String.valueOf(sum2));
                                    mcs.setText(String.valueOf(sum2));
                                }


                            }

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Map<String, Object> map = (Map<String, Object>) ds.getValue();

                                Object mil3 = map.get("Date");
                                String pmil3 = String.valueOf(mil3);
                                String part1[] = pmil3.split("/");

                                String year = yr.getText().toString();

                                if (part1[0].equals(record) && part1[2].equals(year)) {

                                    Object mileage3 = map.get("CO2(Emited)");
                                    String s3 = String.valueOf(mileage3);
                                    float ff2 = new Float(s3).floatValue();

                                    //Double pmileage3 = Double.parseDouble(String.valueOf(mileage3));

                                    sum3 += ff2;

                                    Log.d("Sum", String.valueOf(sum3));
                                    m.setText(String.valueOf(sum3 / 1000.0));
                                }
                            }


                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });






            Spinner spinner = (Spinner) findViewById(R.id.spin);

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, months);


            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                    switch (position) {
                        case 1:
                            record = "1";
                            break;
                        case 2:
                            record = "2";
                            break;
                        case 3:
                            record = "3";
                            break;
                        case 4:
                            record = "4";
                            break;
                        case 5:
                            record = "5";
                            break;
                        case 6:
                            record = "6";
                            break;
                        case 7:
                            record = "7";
                            break;
                        case 8:
                            record = "8";
                            break;
                        case 9:
                            record = "9";
                            break;
                        case 10:
                            record = "10";
                            break;
                        case 11:
                            record = "11";
                            break;
                        case 12:
                            record = "12";
                            break;

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }


            });

        }







}
