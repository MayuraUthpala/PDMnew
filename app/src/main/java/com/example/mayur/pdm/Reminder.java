package com.example.mayur.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Reminder extends AppCompatActivity {

    private RecyclerView mlist;
    private DatabaseReference ref;
    private Firebase data;

    static String currentDate;
    private TextView re1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        data = new Firebase("https://loginapplication-43a51.firebaseio.com/Maintain");

/*
        data.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                Map<String, String> map = dataSnapshot.getValue(Map.class);

                String Date = map.get("Date");
                String Service = map.get("Service").toString();

                    re1.setText(Service);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        */

        ref = FirebaseDatabase.getInstance().getReference().child("Maintain");
        ref.keepSynced(true);

        mlist = (RecyclerView) findViewById(R.id.recy);
        mlist.setHasFixedSize(true);
        mlist.setLayoutManager(new LinearLayoutManager(this));


        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance().format(calendar.getTime());


    }
    /*

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Rem, RemViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Rem, RemViewHolder>
                (Rem.class, R.layout.reminder, RemViewHolder.class, ref) {
            @Override
            protected void populateViewHolder(RemViewHolder viewHolder, Rem model, int position) {

                viewHolder.setDate(model.getDate());
                viewHolder.setService(model.getService());

            }
        };
        mlist.setAdapter(firebaseRecyclerAdapter);
    }
     public static class RemViewHolder extends RecyclerView.ViewHolder {
        android.view.View mview;

        public RemViewHolder(android.view.View itemView)
        {
            super(itemView);
            mview=itemView;
        }

        public void setDate(String Date)
        {

                TextView dat = mview.findViewById(R.id.id_da);
                dat.setText(Date);

        }

        public void setService(String Service)
        {
            TextView se = mview.findViewById(R.id.rem_val);
            se.setText(Service);
        }
   }
*/
}

