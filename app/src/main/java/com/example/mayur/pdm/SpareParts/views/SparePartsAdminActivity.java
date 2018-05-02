package com.example.mayur.pdm.SpareParts.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.mayur.pdm.R;
import com.example.mayur.pdm.SpareParts.adapters.SparePartsAdapter;
import com.example.mayur.pdm.SpareParts.adapters.SparePartsaAdminAdapter;
import com.example.mayur.pdm.SpareParts.models.Sparepart;
import com.example.mayur.pdm.SpareParts.utils.DBHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SparePartsAdminActivity extends AppCompatActivity {

    RecyclerView sparepartsAdminRecyclerView;
    SwipeRefreshLayout sparepaersAdminSwiperefreshLayout;

    Query query;
    ValueEventListener valueEventListener = null;

    ArrayList<Sparepart> sparepartDataSet;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_parts_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SPAddActivity.class);
                intent.putExtra("mode",SPAddActivity.MODE_ADD);
                intent.putExtra("title","Add New Spare part");
                startActivity(intent);
            }
        });

        dbHelper = new DBHelper();

        sparepartsAdminRecyclerView = findViewById(R.id.sparepartsAdminRecyclerView);
        sparepartsAdminRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sparepartsAdminRecyclerView.setItemAnimator(new DefaultItemAnimator());
        sparepaersAdminSwiperefreshLayout = findViewById(R.id.sparepartsAdminSwipeRefreshLayout);

        sparepaersAdminSwiperefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sparepaersAdminSwiperefreshLayout.setRefreshing(true);
                updateDatabaseListener();
                sparepaersAdminSwiperefreshLayout.setRefreshing(false);
            }
        });

        updateDatabaseListener();
    }

    public void updateDatabaseListener() {
        if (valueEventListener != null) {
            query.removeEventListener(valueEventListener);
        }

        query = dbHelper.getSparepartDatabaseReference();
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sparepartDataSet = new ArrayList<>();
                sparepaersAdminSwiperefreshLayout.setRefreshing(true);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Sparepart sparepart = snapshot.getValue(Sparepart.class);
                    sparepart = sparepart.setKey(snapshot.getKey());
                    sparepartDataSet.add(0, sparepart);
                }
                sparepartsAdminRecyclerView.setAdapter(new SparePartsaAdminAdapter(SparePartsAdminActivity.this, sparepartDataSet));
                sparepaersAdminSwiperefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("SpareParts", "Spareparts dailed to retrieve");
            }
        };

        query.addValueEventListener(valueEventListener);

    }


}
