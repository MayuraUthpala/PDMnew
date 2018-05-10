package com.example.mayur.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class admin_rating extends AppCompatActivity {

    private RecyclerView hisList;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private Toolbar mToolbar;
    private FirebaseAuth auth;

    String cid;

    Spinner spinner_num;
    String stars[]={"1","2","3","4","5"};
    ArrayAdapter<String> arrayAdapter;
    private Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_rating);

        mToolbar = (Toolbar) findViewById(R.id.admin_rating_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Rating History");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_num=(Spinner)findViewById(R.id.spinner);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stars);
        spinner_num.setAdapter(arrayAdapter);
        go=(Button)findViewById(R.id.ratingButton);

        hisList=(RecyclerView)findViewById(R.id.recycleadmin);
        hisList.setHasFixedSize(true);
        hisList.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("booking");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String num=spinner_num.getSelectedItem().toString();
                if(num.equals("1")){
                    FirebaseRecyclerAdapter<RatingInfo,RatingViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<RatingInfo, RatingViewHolder>
                            (RatingInfo.class,R.layout.rating_row, RatingViewHolder.class,ref.orderByChild("rating").equalTo(1)) {
                        @Override
                        protected void populateViewHolder(RatingViewHolder viewHolder, RatingInfo model, int position) {
                            viewHolder.setUID(model.getUserId());
                            viewHolder.setRating(model.getRating());
                            viewHolder.setFeedback(model.getFeedback());
                            viewHolder.setPhone(model.getUserId());
                            viewHolder.setDate(model.getDate());
                            viewHolder.setReg(model.getModel());

                        }
                    };
                    hisList.setAdapter(firebaseRecyclerAdapter);
                }
                if(num.equals("2")){
                    FirebaseRecyclerAdapter<RatingInfo,RatingViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<RatingInfo, RatingViewHolder>
                            (RatingInfo.class,R.layout.rating_row, RatingViewHolder.class,ref.orderByChild("rating").equalTo(2)) {
                        @Override
                        protected void populateViewHolder(RatingViewHolder viewHolder, RatingInfo model, int position) {
                            viewHolder.setUID(model.getUserId());
                            viewHolder.setRating(model.getRating());
                            viewHolder.setFeedback(model.getFeedback());
                            viewHolder.setPhone(model.getUserId());
                            viewHolder.setDate(model.getDate());
                            viewHolder.setReg(model.getModel());

                        }
                    };
                    hisList.setAdapter(firebaseRecyclerAdapter);
                }
                if(num.equals("3")){
                    FirebaseRecyclerAdapter<RatingInfo,RatingViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<RatingInfo, RatingViewHolder>
                            (RatingInfo.class,R.layout.rating_row, RatingViewHolder.class,ref.orderByChild("rating").equalTo(3)) {
                        @Override
                        protected void populateViewHolder(RatingViewHolder viewHolder, RatingInfo model, int position) {
                            viewHolder.setUID(model.getUserId());
                            viewHolder.setRating(model.getRating());
                            viewHolder.setFeedback(model.getFeedback());
                            viewHolder.setPhone(model.getUserId());
                            viewHolder.setDate(model.getDate());
                            viewHolder.setReg(model.getModel());

                        }
                    };
                    hisList.setAdapter(firebaseRecyclerAdapter);
                }
                if(num.equals("4")){
                    FirebaseRecyclerAdapter<RatingInfo,RatingViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<RatingInfo, RatingViewHolder>
                            (RatingInfo.class,R.layout.rating_row, RatingViewHolder.class,ref.orderByChild("rating").equalTo(4)) {
                        @Override
                        protected void populateViewHolder(RatingViewHolder viewHolder, RatingInfo model, int position) {
                            viewHolder.setUID(model.getUserId());
                            viewHolder.setRating(model.getRating());
                            viewHolder.setFeedback(model.getFeedback());
                            viewHolder.setPhone(model.getUserId());
                            viewHolder.setDate(model.getDate());
                            viewHolder.setReg(model.getModel());

                        }
                    };
                    hisList.setAdapter(firebaseRecyclerAdapter);
                }
                if(num.equals("5")){
                    FirebaseRecyclerAdapter<RatingInfo,RatingViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<RatingInfo, RatingViewHolder>
                            (RatingInfo.class,R.layout.rating_row, RatingViewHolder.class,ref.orderByChild("rating").equalTo(5)) {
                        @Override
                        protected void populateViewHolder(RatingViewHolder viewHolder, RatingInfo model, int position) {
                            viewHolder.setUID(model.getUserId());
                            viewHolder.setRating(model.getRating());
                            viewHolder.setFeedback(model.getFeedback());
                            viewHolder.setPhone(model.getUserId());
                            viewHolder.setDate(model.getDate());
                            viewHolder.setReg(model.getModel());

                        }
                    };
                    hisList.setAdapter(firebaseRecyclerAdapter);
                }

            }
        });
    }

    public static class RatingViewHolder extends RecyclerView.ViewHolder {
        View mview;

        public RatingViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
        }

        public void setUID(String cid) {
            final TextView cuid = (TextView) mview.findViewById(R.id.displayUID);
            DatabaseReference databaseReference;
            databaseReference=FirebaseDatabase.getInstance().getReference().child("users");
            databaseReference.child(cid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        String name=dataSnapshot.child("lname").getValue().toString();
                        cuid.setText(name);

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        public void setPhone(String cid) {
            final TextView cphone = (TextView) mview.findViewById(R.id.displayPhone);
            DatabaseReference databaseReference;
            databaseReference=FirebaseDatabase.getInstance().getReference().child("users");
            databaseReference.child(cid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        String p=dataSnapshot.child("phone").getValue().toString();
                        cphone.setText(p);

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        public void setRating(Long star) {
            TextView cstar = (TextView) mview.findViewById(R.id.displayRating);
            cstar.setText(String.valueOf(star));
        }

        public void setDate(String date){
            TextView cdate=(TextView)mview.findViewById(R.id.displayDate);
            cdate.setText(date);
        }

        public void setReg(String reg){
            TextView creg=(TextView)mview.findViewById(R.id.displayReg);
            creg.setText(reg);
        }
        public void setFeedback(String feed){
            TextView cfeed=(TextView)mview.findViewById(R.id.displayFeedback);
            cfeed.setText(feed);
        }
    }
}
