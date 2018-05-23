package com.example.mayur.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListOFBookings extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ofbookings);

        listView=(ListView)findViewById(R.id.bookinglist);
        database= FirebaseDatabase.getInstance();
        ref=database.getReference("Bookings").child("LCo9TJkGU6AE0T3Ccjk");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //String string=dataSnapshot.getValue(String.class);
                String key1 = dataSnapshot.child("Date").getValue(String.class);
                String key2 = dataSnapshot.child("Model").getValue(String.class);
                String key3 = dataSnapshot.child("ServiceType").getValue(String.class);
                String key4 = dataSnapshot.child("ServiceCharge").getValue(String.class);
                String key5 = dataSnapshot.child("TimeSlot").getValue(String.class);
                String key6 = dataSnapshot.child("UserId").getValue(String.class);
                String key7 = dataSnapshot.child("VehicleNO").getValue(String.class);
                //String key2 = dataSnapshot.child("key2").getValue(String.class);
                arrayList.add(key1);
                arrayList.add(key2);
                arrayList.add(key3);
                arrayList.add(key4);
                arrayList.add(key5);
                arrayList.add(key6);
                arrayList.add(key7);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
