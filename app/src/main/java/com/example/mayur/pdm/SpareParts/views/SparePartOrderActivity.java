package com.example.mayur.pdm.SpareParts.views;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.mayur.pdm.R;
import com.example.mayur.pdm.SpareParts.adapters.SparePartsOrderAdminAdapter;
import com.example.mayur.pdm.SpareParts.models.CartSparepart;
import com.example.mayur.pdm.SpareParts.utils.DBHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SparePartOrderActivity extends AppCompatActivity {
    RecyclerView sparepartsOrderRecyclerView;
    SwipeRefreshLayout sparepaersOrderSwiperefreshLayout;

    Query query;
    ValueEventListener valueEventListener = null;

    ArrayList<CartSparepart> sparepartOrderDataSet;


    DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_part_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sparepartsOrder_tool_bar);
        toolbar.setTitle("Spare Part Orders");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper();
        sparepartsOrderRecyclerView = findViewById(R.id.sparepartsOrderRecyclerView);
        sparepartsOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sparepartsOrderRecyclerView.setItemAnimator(new DefaultItemAnimator());
        sparepaersOrderSwiperefreshLayout = findViewById(R.id.sparepartsOrderSwipeRefreshLayout);

        sparepaersOrderSwiperefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sparepaersOrderSwiperefreshLayout.setRefreshing(true);
                updateDatabaseListener();
                sparepaersOrderSwiperefreshLayout.setRefreshing(false);

            }
        });
        updateDatabaseListener();
    }



    private void updateDatabaseListener() {
        if (valueEventListener != null) {
            query.removeEventListener(valueEventListener);
        }

//        query = dbHelper.getDatabaseReference(path);
        query = dbHelper.getSparepartOrdersDatabaseReference();
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sparepartOrderDataSet = new ArrayList<>();
                sparepaersOrderSwiperefreshLayout.setRefreshing(true);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CartSparepart cartsparepart = snapshot.getValue(CartSparepart.class);
                    cartsparepart = cartsparepart.setKey(snapshot.getKey());
                    Log.d("SPARELOG", "Order activity : " + cartsparepart.name);
                    sparepartOrderDataSet.add(0, cartsparepart);
                }
                sparepartsOrderRecyclerView.setAdapter(new SparePartsOrderAdminAdapter(SparePartOrderActivity.this,sparepartOrderDataSet));
                sparepaersOrderSwiperefreshLayout.setRefreshing(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("CartSparepart", "Sparepart Orders failed to retrieve");
            }
        };
        query.addValueEventListener(valueEventListener);
    }
}
