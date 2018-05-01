package com.example.mayur.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class booking_history extends AppCompatActivity {

    private ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Toolbar mToolbar;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        cid = auth.getCurrentUser().getUid();

        mToolbar = (Toolbar) findViewById(R.id.history_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Service History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.db_booklist);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("booking");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        final Query query=FirebaseDatabase.getInstance().getReference().child("booking").orderByChild("UserId")
                .equalTo(cid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String date = ds.child("Date").getValue(String.class);
                    String stype = ds.child("SecrviceType").getValue(String.class);
                    String vno=ds.child("VehicleNo").getValue(String.class);
                    arrayList.add(date);
                    arrayList.add(stype);
                    arrayList.add(vno);
                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

