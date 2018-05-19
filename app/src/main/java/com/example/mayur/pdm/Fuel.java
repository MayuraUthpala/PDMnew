package com.example.mayur.pdm;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.Calendar;

import static android.R.style.Theme_Holo_Light_Dialog_MinWidth;

/**
 * Created by Dulip on 4/8/2018.
 */

public class Fuel extends Activity {
    private TextView sbut;
    private TextView cdate;
    private TextView cdat;
    private TextView odo;
    private TextView qty;
    private TextView pri;
    private TextView cos;
    private TextView km;
    private TextView em;
    private TextView kil;
    private TextView can;

    private EditText eodo;
    private EditText eqty;
    private EditText epri;
    private EditText ecos;
    private EditText ekm;
    private EditText eem;
    private EditText ekil;

    private static final String TAG = "Fuel";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private Firebase mRootref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuel);
        can = findViewById(R.id.txcan);

        CardView sta = findViewById(R.id.f2);
        sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(Fuel.this,Stat.class);
                startActivity(f);
            }
        });

        can.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fuel.this,Fueldul.class);
                startActivity(i);
            }


        });

        mRootref = new Firebase("https://loginapplication-43a51.firebaseio.com/Fuel");

        //getting ids of keys
        cdate = findViewById(R.id.id_dat);
        odo = findViewById(R.id.id_odo);
        qty = findViewById(R.id.id_qty);
        pri = findViewById(R.id.id_pri);
        cos =  findViewById(R.id.id_cos);
        km = findViewById(R.id.id_co);
        em = findViewById(R.id.id_em);
        kil = findViewById(R.id.id_kil);

        //getting ids of values
        cdat = findViewById(R.id.tvdate);
        eodo = findViewById(R.id.id_eo);
        eqty = findViewById(R.id.edit2);
        epri = findViewById(R.id.edit3);
        ecos = findViewById(R.id.edit4);
        ekm = findViewById(R.id.edit5);
        eem = findViewById(R.id.edit6);
        ekil = findViewById(R.id.id_ekil);

        //button
        sbut = findViewById(R.id.txs);

        em.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Double c = Double.parseDouble(eodo.getText().toString());
                Double c1 = Double.parseDouble(ekm.getText().toString());
                Double re = c*c1;
                eem.setText(String.valueOf(re));
            }
        });

        qty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Double var = Double.parseDouble(eodo.getText().toString());
                    Double var3 = Double.parseDouble(ekil.getText().toString());
                    Double vqt = var / var3;
                    eqty.setText(String.valueOf(vqt));
            }
        });
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Double var1 = Double.parseDouble(epri.getText().toString());
                Double var2 = Double.parseDouble(eqty.getText().toString());
                Double res = var1*var2;
                ecos.setText(String.valueOf(res));
            }
        });


        sbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    //add keys
                    String key1 = cdate.getText().toString();
                    String key2 = odo.getText().toString();
                    String key3 = qty.getText().toString();
                    String key4 = pri.getText().toString();
                    String key5 = cos.getText().toString();
                    String key6 = km.getText().toString();
                    String key7 = em.getText().toString();
                    String key8 = kil.getText().toString();

                    //add values
                    String value1 = cdat.getText().toString();

                    String value2 = eodo.getText().toString();
                    Double val2 = Double.parseDouble(value2);

                    String value3 = eqty.getText().toString();
                    Double val3 = Double.parseDouble(value3);

                    String value4 = epri.getText().toString();
                    Double val4 = Double.parseDouble(value4);

                    String value5 = ecos.getText().toString();
                    Double val5 = Double.parseDouble(value5);

                    String value6 = ekm.getText().toString();
                    Double val6 = Double.parseDouble(value6);

                    String value7 = eem.getText().toString();
                    Double val7 = Double.parseDouble(value7);

                    String value8 = ekil.getText().toString();
                    Double val8 = Double.parseDouble(value8);

                    //calculations


                    //adding to database
                    Firebase childRef = mRootref.push();
                    childRef.child(key1).setValue(value1);

                    Firebase childRef1 = mRootref.push();
                    childRef.child(key2).setValue(val2);

                    Firebase childref8 = mRootref.push();
                    childRef.child(key8).setValue(val8);

                    Firebase childRef2 = mRootref.push();
                    childRef.child(key3).setValue(val3);

                    Firebase childRef3 = mRootref.push();
                    childRef.child(key4).setValue(val4);

                    Firebase childRef4 = mRootref.push();
                    childRef.child(key5).setValue(val5);

                    Firebase childRef5 = mRootref.push();
                    childRef.child(key6).setValue(val6);

                    Firebase childRef6 = mRootref.push();
                    childRef.child(key7).setValue(val7);

            }
        });

        mDisplayDate = (TextView) findViewById(R.id.tvdate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(
                        Fuel.this,
                        Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                Log.d(TAG,"onDateSet: mm/dd/yyy "+month+"/"+day+"/"+year);
                String date = month+"/"+day+"/"+year;
                mDisplayDate.setText(date);
            }
        };
    }
}
