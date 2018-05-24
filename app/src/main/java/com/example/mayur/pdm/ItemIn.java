package com.example.mayur.pdm;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Calendar;

import static android.R.style.Theme_Holo_Light_Dialog_MinWidth;

public class ItemIn extends AppCompatActivity {
    TextView tx1,tx2,tx3,tx4,tx5,tx6,tx8,tx9,tx10,teser,tk;
    EditText tx7;


    private Firebase mrootre;


    private static final String TAG = "IN";
    private TextView LDisplayDate;
    private DatePickerDialog.OnDateSetListener LDateSetListener;

    ArrayAdapter<String> adapter;
    Spinner spinner;

    String record="";

    String servs[] = {"Service","Transmission Fluid","Battery and Cables","Belts","Engine Air Filter","Engine Oil","Exhaust","Hoses","Lights","Steering Fluid","Tire Inflation and Condition","Windshield Washer Fluid","Dashboard Indicator Light","Chassis Lubrication","Viper Blades","Brakes","Cabin Air Filter","Coolant (Antifreeze)","Steering and Suspension","Wheel Alignment"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_in);

       mrootre = new Firebase("https://loginapplication-43a51.firebaseio.com/Maintain");

        tx1 = findViewById(R.id.txtcan);
        tx2 = findViewById(R.id.text2);

        tx3 = findViewById(R.id.txtsav);

       teser = findViewById(R.id.sern1);
       // teser.setText("Service \t \t"+getIntent().getStringExtra("Service"));


        //keys

        tx4 = findViewById(R.id.ndat);
        tx6 = findViewById(R.id.tci);
        tx8 = findViewById(R.id.rep);

        //values
        tx9 = findViewById(R.id.serdat);
        tx7 = findViewById(R.id.nexdat);
        tx10 = findViewById(R.id.id_erep);


        tx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              //  String part[] = ii.split("\t \t");
                String ky = teser.getText().toString();
                String key1 = tx4.getText().toString();
                String key2 = tx6.getText().toString();
                String key3 = tx8.getText().toString();

                String valu1 = tx9.getText().toString();
                String valu2 = tx7.getText().toString();
                String valu3 = tx10.getText().toString();

                if (TextUtils.isEmpty(valu1)||TextUtils.isEmpty(valu2)||TextUtils.isEmpty(valu3))
                {
                    Toast.makeText(ItemIn.this, "Every fields should be filled", Toast.LENGTH_SHORT).show();
                    return;

                }
                else {

                    Firebase childrk = mrootre.child(record);
                    childrk.child(ky).setValue(record);

                    Firebase childr = mrootre.child(key1);
                    childrk.child(key1).setValue(valu1);


                    Firebase childr1 = mrootre.child(key2);
                    childrk.child(key2).setValue(valu2);
                    Firebase childr2 = mrootre.child(key3);
                    childrk.child(key3).setValue(valu3);

                    Toast.makeText(ItemIn.this, "Successfully Updated", Toast.LENGTH_SHORT).show();

                }


            }
        });


        LDisplayDate = (TextView) findViewById(R.id.serdat);
        LDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(
                        ItemIn.this,
                        Theme_Holo_Light_Dialog_MinWidth,LDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        LDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                Log.d(TAG,"onDateSet: mm/dd/yyy "+month+"/"+day+"/"+year);
                String date = month+"/"+day+"/"+year;
                LDisplayDate.setText(date);
            }
        };



         spinner = (Spinner) findViewById(R.id.spin1);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,servs);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position) {
                    case 1:
                        record= "Transmission Fluid";
                        break;
                    case 2:
                        record= "Battery and Cables";
                        break;
                    case 3:
                        record= "Belts";
                        break;
                    case 4:
                        record= "Engine Air Filter";
                        break;
                    case 5:
                        record= "Engine Oil";
                        break;
                    case 6:
                        record= "Exhaust";
                        break;
                    case 7:
                        record= "Hoses";
                        break;
                    case 8:
                        record= "Lights";
                        break;
                    case 9:
                        record= "Steering Fluid";
                        break;
                    case 10:
                        record= "Tire Inflation and Condition";
                        break;
                    case 11:
                        record= "Windshield Washer Fluid";
                        break;
                    case 12:
                        record= "Dashboard Indicator Light";
                        break;
                    case 13:
                        record= "Chassis Lubrication";
                        break;
                    case 14:
                        record= "Viper Blades";
                        break;
                    case 15:
                        record= "Brakes";
                        break;
                    case 16:
                        record= "Cabin Air Filter";
                        break;
                    case 17:
                        record= "Coolant (Antifreeze)";
                        break;
                    case 18:
                        record= "Steering and Suspension";
                        break;
                    case 19:
                        record= "Wheel Alignment";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

            });

    }
}


