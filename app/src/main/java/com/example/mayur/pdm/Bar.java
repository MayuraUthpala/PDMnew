package com.example.mayur.pdm;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Bar extends AppCompatActivity {

    TextView tx1,tx2,tx3,tx4;

    private BarChart barChart;
    private BarData barData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        tx1 = findViewById(R.id.id_emil1);
        tx2 = findViewById(R.id.id_efu1);
        tx3 = findViewById(R.id.id_ecs1);
        tx4 = findViewById(R.id.id_eemi1);

        tx1.setText(getIntent().getStringExtra("Mileage"));
        tx2.setText(getIntent().getStringExtra("Fuel Consumption"));
        tx3.setText(getIntent().getStringExtra("Total Cost"));
        tx4.setText(getIntent().getStringExtra("CO2 Emission"));


        //barChart
        barChart = (BarChart) findViewById(R.id.barc);
        barData = new BarData(getXvalues(), getBarvalues());
        barData.setValueTextColor(Color.rgb(0, 0, 0));

        barChart.setData(barData);
        barChart.setBackgroundColor(Color.rgb(255, 255, 255));
        barChart.getXAxis().setTextColor(Color.rgb(0, 0, 0));
        barChart.setGridBackgroundColor(Color.rgb(255, 255, 255));
        barChart.animateXY(2000, 2000);
        barChart.invalidate();


    }

    private ArrayList<BarDataSet> getBarvalues() {
        ArrayList<BarDataSet> barDataSets;

        ArrayList<BarEntry> barEntries = new ArrayList<>();


        Double val1 = Double.parseDouble(tx1.getText().toString());
        Double val2 = Double.parseDouble(tx2.getText().toString());
        Double val3 = Double.parseDouble(tx3.getText().toString());
        Double val4 = Double.parseDouble(tx4.getText().toString());
        float g =val1.floatValue();
        float g1 =val2.floatValue();
        float g2 =val3.floatValue();
        float g3 =val4.floatValue();

        BarEntry barEntry1 = new BarEntry(g, 0);
        BarEntry barEntry2 = new BarEntry(g1, 1);
        BarEntry barEntry3 = new BarEntry(g2, 2);
        BarEntry barEntry4 = new BarEntry(g3, 3);
        barEntries.add(barEntry1);
        barEntries.add(barEntry2);
        barEntries.add(barEntry3);
        barEntries.add(barEntry4);


        BarDataSet barDataSet = new BarDataSet(barEntries, "Monthly Rates");
        barDataSets = new ArrayList<>();
        barDataSets.add(barDataSet);

        barDataSet.setColor(Color.rgb(150,6,6));

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
