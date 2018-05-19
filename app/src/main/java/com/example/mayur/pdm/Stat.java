package com.example.mayur.pdm;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Stat extends AppCompatActivity {

    private BarChart barChart;
    private BarData barData;

    private TextView mdis;
    private TextView mcs;
    private TextView fu;
    private TextView m;
    private TextView gene;

    private EditText yr;
    private DatabaseReference mdata;
    String t1,t2,t3,t4;

    float moodarr[] = {5.0f, 25.0f, 6.7f,5.3f};

    float f1 = 0f;
    float f2 = 0f;
    float f3 = 0f;
    float f4 = 0f;

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

        yr = findViewById(R.id.id_eyear);

        t1 = mdis.getText().toString();
        t2 = mcs.getText().toString();
        t3 = fu.getText().toString();
        t4 = m.getText().toString();

        gene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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
                                m.setText(String.valueOf(sum3));
                            }
                        }

                    }





    /*        private ArrayList<BarDataSet> getBarvalues() {
                ArrayList<BarDataSet> barDataSets;

                ArrayList<BarEntry> barEntries = new ArrayList<>();
*/

                    /*   Double val1 = Double.parseDouble(mdis.getText().toString());
                       Double val2 = Double.parseDouble(mcs.getText().toString());
                       Double val3 = Double.parseDouble(fu.getText().toString());
                       Double val4 = Double.parseDouble(m.getText().toString());
                       float g =val1.floatValue();
                       float g1 =val2.floatValue();
                       float g2 =val3.floatValue();
                       float g3 =val4.floatValue();
               */
  /*              String t1 = mdis.getText().toString();
                String t2 = mcs.getText().toString();
                String t3 = fu.getText().toString();
                String t4 = m.getText().toString();


                float f1 = new Float(t1).floatValue();
                float f2 = new Float(t2).floatValue();
                float f3 = new Float(t3).floatValue();
                float f4 = new Float(t4).floatValue();


                BarEntry barEntry1 = new BarEntry(f1, 0);
                BarEntry barEntry2 = new BarEntry(f2, 1);
                BarEntry barEntry3 = new BarEntry(f3, 2);
                BarEntry barEntry4 = new BarEntry(f4, 3);
                barEntries.add(barEntry1);
                barEntries.add(barEntry2);
                barEntries.add(barEntry3);
                barEntries.add(barEntry4);


                //instead
        for (int i=0;i<moodarr.length; i++)
        {
            barEntries.add(new BarEntry(moodarr[i],i));
        }

                BarDataSet barDataSet = new BarDataSet(barEntries, "Monthly Rates");
                barDataSets = new ArrayList<>();
                barDataSets.add(barDataSet);

                barDataSet.setColor(Color.YELLOW);

                return barDataSets;
            }

            private ArrayList<String> getXvalues() {

                ArrayList<String> xvalues = new ArrayList<>();
                xvalues.add("Milage");
                xvalues.add("Fuel Consumption");
                xvalues.add("Cost");
                xvalues.add("CO2 Emission");

                return xvalues;
            }

*/
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });





            //barChart
            barChart = (BarChart) findViewById(R.id.barc);
            barData = new BarData(getXvalues(), getBarvalues());
            barData.setValueTextColor(Color.rgb(25, 103, 97));

            barChart.setData(barData);
            barChart.setBackgroundColor(Color.rgb(18, 49, 46));
            barChart.getXAxis().setTextColor(Color.rgb(25, 103, 97));
            barChart.setGridBackgroundColor(Color.rgb(18, 63, 60));
            barChart.animateXY(3000, 3000);
            barChart.invalidate();


            Spinner spinner = (Spinner) findViewById(R.id.spin);

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, months);
            // Create an ArrayAdapter using the string array and a default spinner layout
            // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.month_array, android.R.layout.simple_spinner_item);

            // Specify the layout to use when the list of choices appears
            // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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



/*
       float f1=1000.6f;
       float f2=1054.6f;
       float f3=2006.0f;
       float f4=1504.5f;
       */
     /*   Double val1 = Double.parseDouble(mdis.getText().toString());
        Double val2 = Double.parseDouble(mcs.getText().toString());
        Double val3 = Double.parseDouble(fu.getText().toString());
        Double val4 = Double.parseDouble(m.getText().toString());
        float g =val1.floatValue();
        float g1 =val2.floatValue();
        float g2 =val3.floatValue();
        float g3 =val4.floatValue();
*/
    /*  String t1 = String.valueOf(mdis.getText().toString());
        String t2 = String.valueOf(mcs.getText().toString());
        String t3 = String.valueOf(fu.getText().toString());
        String t4 = String.valueOf(m.getText().toString());
*/
/*
       float p1 = new Float(t1).floatValue();
       f1=Float.valueOf(p1);
        float fi = new Float(t2).floatValue();
        f2 = Float.valueOf(fi);
        float p3 = new Float(t3).floatValue();
        f3 = Float.valueOf(p3);
        float p4 = new Float(t4).floatValue();
        f4 = Float.valueOf(p4);


        BarEntry barEntry1 = new BarEntry(f1, 0);
        BarEntry barEntry2 = new BarEntry(f2, 1);
        BarEntry barEntry3 = new BarEntry(f3, 2);
        BarEntry barEntry4 = new BarEntry(f4, 3);
        barEntries.add(barEntry1);
        barEntries.add(barEntry2);
        barEntries.add(barEntry3);
        barEntries.add(barEntry4);
*/

        //instead



    private ArrayList<BarDataSet> getBarvalues() {
        ArrayList<BarDataSet> barDataSets;

        ArrayList<BarEntry> barEntries = new ArrayList<>();


               /*        Double val1 = Double.parseDouble(mdis.getText().toString());
                       Double val2 = Double.parseDouble(mcs.getText().toString());
                       Double val3 = Double.parseDouble(fu.getText().toString());
                       Double val4 = Double.parseDouble(m.getText().toString());
                       float g =val1.floatValue();
                       float g1 =val2.floatValue();
                       float g2 =val3.floatValue();
                       float g3 =val4.floatValue(); */
/*
                String t1 = mdis.getText().toString();
                String t2 = mcs.getText().toString();
                String t3 = fu.getText().toString();
                String t4 = m.getText().toString();


                float f1 = new Float(t1).floatValue();
                float f2 = new Float(t2).floatValue();
                float f3 = new Float(t3).floatValue();
                float f4 = new Float(t4).floatValue();


                BarEntry barEntry1 = new BarEntry(f1, 0);
                BarEntry barEntry2 = new BarEntry(f2, 1);
                BarEntry barEntry3 = new BarEntry(f3, 2);
                BarEntry barEntry4 = new BarEntry(f4, 3);
                barEntries.add(barEntry1);
                barEntries.add(barEntry2);
                barEntries.add(barEntry3);
                barEntries.add(barEntry4);
*/

                //instead
        for (int i=0;i<moodarr.length; i++)
        {
            barEntries.add(new BarEntry(moodarr[i],i));
        }

                BarDataSet barDataSet = new BarDataSet(barEntries, "Monthly Rates");
                barDataSets = new ArrayList<>();
                barDataSets.add(barDataSet);

                barDataSet.setColor(Color.rgb(25,103,97));

                return barDataSets;
            }

    private ArrayList<String> getXvalues() {

        ArrayList<String> xvalues = new ArrayList<>();
        xvalues.add("Milage");
        xvalues.add("Fuel Consumption");
        xvalues.add("Cost");
        xvalues.add("CO2 Emission");

        return xvalues;
    }


}
