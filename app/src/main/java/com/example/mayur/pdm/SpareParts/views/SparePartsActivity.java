package com.example.mayur.pdm.SpareParts.views;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.example.mayur.pdm.R;
import com.example.mayur.pdm.SpareParts.adapters.SparePartsAdapter;
import com.example.mayur.pdm.SpareParts.models.Sparepart;
import com.example.mayur.pdm.SpareParts.utils.DBHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SparePartsActivity extends AppCompatActivity {

    RecyclerView sparepartsRecyclerView;
    SwipeRefreshLayout sparepaersSwiperefreshLayout;

    Query query;
    ValueEventListener valueEventListener = null;

    ArrayList<Sparepart> sparepartDataSet;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_parts);
        Toolbar toolbar = findViewById(R.id.spareparts_tool_bar);
        toolbar.setTitle("Spare Parts");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper();

        sparepartsRecyclerView = findViewById(R.id.sparepartsRecyclerView);
        sparepartsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sparepartsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        sparepaersSwiperefreshLayout = findViewById(R.id.sparepartsSwipeRefreshLayout);

        sparepaersSwiperefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sparepaersSwiperefreshLayout.setRefreshing(true);
//                String path = "Audi/" + "A06/" + "2018/" + "Cooling and Heating/"+"Intercooler";
                updateDatabaseListener();
                sparepaersSwiperefreshLayout.setRefreshing(false);
            }
        });

//        Sparepart sparepart = new Sparepart("BMW", "1 Series", "2017", "Brakes", "Brake Discs", "Eicher Premium Brake Disc", "Used", "10000", 2, "Front");
//        String path = "Audi/" + "A06/" + "2018/" + "Cooling and Heating/"+"Intercooler";
//        dbHelper.pushSparepart(path, sparepart);

        updateDatabaseListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_spareparts, menu);
        return true;
    }

    public void updateDatabaseListener() {
        if (valueEventListener != null) {
            query.removeEventListener(valueEventListener);
        }

//        query = dbHelper.getDatabaseReference(path);
        query = dbHelper.getSparepartDatabaseReference();
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sparepartDataSet = new ArrayList<>();
                sparepaersSwiperefreshLayout.setRefreshing(true);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Sparepart sparepart = snapshot.getValue(Sparepart.class);
                    sparepart = sparepart.setKey(snapshot.getKey());
                    sparepartDataSet.add(0, sparepart);
                }
                sparepartsRecyclerView.setAdapter(new SparePartsAdapter(SparePartsActivity.this, sparepartDataSet));
                sparepaersSwiperefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("SpareParts", "Spareparts dailed to retrieve");
            }
        };

        query.addValueEventListener(valueEventListener);

    }
}
