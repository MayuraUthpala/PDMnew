package com.example.mayur.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class bhistory extends AppCompatActivity {

    private RecyclerView hisList;
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
        setContentView(R.layout.activity_bhistory);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        cid = auth.getCurrentUser().getUid();

        mToolbar = (Toolbar) findViewById(R.id.history_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Service History");
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hisList=(RecyclerView)findViewById(R.id.myrec);
        hisList.setHasFixedSize(true);
        hisList.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("booking");
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<history,HistoryViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<history, HistoryViewHolder>
                (history.class,R.layout.book_row,HistoryViewHolder.class,ref.orderByChild("UserId").equalTo(cid)) {
            @Override
            protected void populateViewHolder(HistoryViewHolder viewHolder, history model, int position) {
                viewHolder.setDate(model.getDate());
                viewHolder.setModel(model.getModel());
                viewHolder.setReg(model.getVehicleNo());
                viewHolder.setType(model.getSecrviceType());

            }
        };
        hisList.setAdapter(firebaseRecyclerAdapter);


    }
    public static class HistoryViewHolder extends RecyclerView.ViewHolder{
        View mview;
        public HistoryViewHolder(View itemView){
            super(itemView);
            mview=itemView;
        }
        public void setDate(String date){
            TextView cdate=(TextView)mview.findViewById(R.id.displayDate);
            cdate.setText(date);
        }
        public void setType(String type){
            TextView ctype=(TextView)mview.findViewById(R.id.displayType);
            ctype.setText(type);
        }
        public void setReg(String reg){
            TextView creg=(TextView)mview.findViewById(R.id.displayReg);
            creg.setText(reg);
        }
        public void setModel(String model){
            TextView cmodel=(TextView)mview.findViewById(R.id.displayModel);
            cmodel.setText(model);
        }


    }



}
