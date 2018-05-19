package com.example.mayur.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RetrieveBooking extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_booking);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Bookings");
        databaseReference.keepSynced(true);

        recyclerView=(RecyclerView)findViewById(R.id.myrec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<retbookings,getBookings>FirebaseRecyclerAdapter=new FirebaseRecyclerAdapter<retbookings, getBookings>
                (retbookings.class,R.layout.viewbookings,getBookings.class,databaseReference) {
            @Override
            protected void populateViewHolder(getBookings viewHolder, retbookings model, int position) {
                viewHolder.setUserId(model.getUserId());
                viewHolder.setServiceType(model.getServiceType());
                viewHolder.setServiceCharge(model.getServiceCharge());
                viewHolder.setVehicleNo(model.getVehicleNo());
                viewHolder.setModel(model.getModel());
                viewHolder.setDate(model.getDate());
                viewHolder.setTimeSlot(model.getTimeSlot());
            }
        };
        recyclerView.setAdapter(FirebaseRecyclerAdapter);
    }

    public static class getBookings extends RecyclerView.ViewHolder{

        View mview;
        public getBookings(View itemView) {
            super(itemView);
            mview=itemView;
        }

        public void setUserId(String userId) {
            TextView tw1=(TextView)mview.findViewById(R.id.userId);
            tw1.setText(userId);
            //UserId = userId;
        }

        public void setServiceType(String serviceType) {
            TextView tw2=(TextView)mview.findViewById(R.id.servtp);
            tw2.setText(serviceType);
            //ServiceType = serviceType;
        }

        public void setServiceCharge(String serviceCharge) {
            TextView tw3=(TextView)mview.findViewById(R.id.vno);
            tw3.setText(serviceCharge);
            //ServiceCharge = serviceCharge;
        }

        public void setModel(String model) {
            TextView tw4=(TextView)mview.findViewById(R.id.make);
            tw4.setText(model);
            //Model = model;
        }

        public void setVehicleNo(String vehicleNo) {
            TextView tw5=(TextView)mview.findViewById(R.id.vnm);
            tw5.setText(vehicleNo);
            //VehicleNo = vehicleNo;
        }

        public void setDate(String date) {
            TextView tw6=(TextView)mview.findViewById(R.id.bookdate);
            tw6.setText(date);
            //Date = date;
        }

        public void setTimeSlot(String timeSlot) {
            TextView tw7=(TextView)mview.findViewById(R.id.booktime);
            tw7.setText(timeSlot);
            //TimeSlot = timeSlot;
        }

    }
}
