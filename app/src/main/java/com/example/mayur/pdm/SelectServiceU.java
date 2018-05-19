package com.example.mayur.pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SelectServiceU extends AppCompatActivity {

    ListView lstvv;
    FirebaseDatabase database;
    DatabaseReference ref;
    //DatabaseReference ref2;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service_u);

        lstvv=(ListView)findViewById(R.id.lvusr);
        database= FirebaseDatabase.getInstance();
        ref=database.getReference("services");
        //ref2=ref.child("ServiceName");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
        lstvv.setAdapter(adapter);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int count=0;
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    String  value = childSnapshot.getValue(String.class);
                    if(count%2==0){
                        arrayList.add(value);
                    }
                    count=count+1;
                }
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
            lstvv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent=new Intent(SelectServiceU.this,bookingServiceU.class);
                    intent.putExtra("ServiceName",adapter.getItem(i));
                    startActivity(intent);
                }
            });

    }
}
