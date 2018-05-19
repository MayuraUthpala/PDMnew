package com.example.mayur.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminBooking extends AppCompatActivity {

    private RecyclerView rec;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<retbookings> arrayList;
    //ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking);

        rec = (RecyclerView) findViewById(R.id.myrec);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("booking");
        ref.keepSynced(true);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<retbookings, bookViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<retbookings, bookViewHolder>
                (retbookings.class, R.layout.viewbookings, bookViewHolder.class, ref) {
            @Override
            protected void populateViewHolder(bookViewHolder viewHolder, retbookings model, int position) {
                viewHolder.setUserId(model.getUserId());
                viewHolder.setServiceType(model.getServiceType());
                viewHolder.setModel(model.getModel());
                viewHolder.setVehicleNo(model.getVehicleNo());
                viewHolder.setDate(model.getDate());
                viewHolder.setTimeSlot(model.getTimeSlot());
            }
        };
        rec.setAdapter(firebaseRecyclerAdapter);
    }

        public static class bookViewHolder extends RecyclerView.ViewHolder
        {
            View mview;
            public bookViewHolder(View itemview)
            {
                super(itemview);
                mview=itemview;

            }

            public void setUserId(String userId) {
                TextView tw1=(TextView)mview.findViewById(R.id.userId);
                tw1.setText(userId);
            }
            public void setServiceType(String serviceType) {

                TextView tw2=(TextView)mview.findViewById(R.id.servtp);
                tw2.setText(serviceType);
            }
            public void setModel(String model) {

               // TextView tw3=(TextView)mview.findViewById(R.id.mk);
                //tw3.setText(model);
            }



            public void setVehicleNo(String vehicleNo) {

                TextView tw4=(TextView)mview.findViewById(R.id.vno);
                tw4.setText(vehicleNo);
            }


            public void setDate(String date) {

                TextView tw5=(TextView)mview.findViewById(R.id.bookdate);
                tw5.setText(date);
            }



            public void setTimeSlot(String timeSlot) {

                TextView tw6=(TextView)mview.findViewById(R.id.booktime);
                tw6.setText(timeSlot);
            }
        }
    }


