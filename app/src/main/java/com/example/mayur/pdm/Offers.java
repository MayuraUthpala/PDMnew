package com.example.mayur.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Offers extends AppCompatActivity {

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    EditText itemText;
    Button addButton;
    ListView lv;

    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        lv = (ListView) findViewById(R.id.lstvv);
        itemText = (EditText) findViewById(R.id.edittxt);
        addButton = (Button) findViewById(R.id.offerbtn);
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Offers");

//        database= FirebaseDatabase.getInstance();
//        ref=database.getReference("Offers");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(adapter);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String string = dataSnapshot.getValue(String.class);
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

        final ArrayList<String> keys = new ArrayList<>();
        final ArrayList<String> values = new ArrayList<>();
        ref.getRoot().child("Offers")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messages : dataSnapshot.getChildren()) {
                            keys.add(messages.getKey());
                            values.add(messages.getValue(String.class));
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
        /*handle errors*/
                       // Toast.makeText(getApplicationContext(), "Can not delete..DB error!!!", Toast.LENGTH_SHORT).show();
                    }
                });

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        values.remove(i);
                        adapter.notifyDataSetChanged();
                        //new code below
                        ref.getRoot().child("Offers").child(keys.get(i)).removeValue();
                        keys.remove(i);
                        Toast.makeText(getApplicationContext(), "Succesfully removed...", Toast.LENGTH_SHORT).show();
                    }
                });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.push().setValue(itemText.getText().toString());
                Toast.makeText(getApplicationContext(), "Saved to database", Toast.LENGTH_SHORT).show();
                itemText.setText("");
            }


        });

    }
}