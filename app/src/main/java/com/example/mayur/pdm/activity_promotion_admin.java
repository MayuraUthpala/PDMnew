package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_promotion_admin extends AppCompatActivity {

    private Button Add;
    private Toolbar mToolbar;
    private ListView listView;
    private FirebaseDatabase database;
    private ArrayList<String> arrayList=new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_admin);

        mToolbar=(Toolbar) findViewById(R.id.prom_admin);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Promotions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));


        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Promotion");


        Add = (Button) findViewById(R.id.btn_submitPromotion);
        final EditText promtext = (EditText) findViewById(R.id.promotionadd);
        listView=(ListView) findViewById(R.id.db_promlist);


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String string=dataSnapshot.getValue(String.class);
                arrayList.add(string);
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
        //credit to https://stackoverflow.com/questions/44700447/when-click-on-listview-delete-it-and-delete-associated-data-with-it-in-firebas

        final ArrayList<String> keyList = new ArrayList<>();
        final ArrayList<String> items = new ArrayList<>();
        ref.getRoot().child("Promotion")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messages : dataSnapshot.getChildren()) {
                            keyList.add(messages.getKey());
                            items.add(messages.getValue(String.class));
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        /*handle errors*/
                    }
                });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adapter.remove(adapter.getItem(position));
                adapter.notifyDataSetChanged();
                //new code below
                ref.getRoot().child("Promotion").child(keyList.get(position)).removeValue();
                keyList.remove(position);

            }
        });



        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.push().setValue(promtext.getText().toString());
                Toast.makeText(getApplicationContext(),"Saved to database",Toast.LENGTH_SHORT).show();
                promtext.setText("");


                       /* new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(getApplicationContext(),"Saved to database",Toast.LENGTH_SHORT).show();
                    }
                };*/





            }
        });
    }
}
