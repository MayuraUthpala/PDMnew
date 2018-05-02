package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Maintain extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Firebase mref;
    private DatabaseReference myref;



    FirebaseDatabase database;

    private ArrayList<String> mvals;

    private ImageView im;

    String child;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain);

        im = (ImageView) findViewById(R.id.id_add);
            //new
            myref= FirebaseDatabase.getInstance().getReference().child("Maintain");
            myref.keepSynced(true);



            im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent f = new Intent(Maintain.this,ItemIn.class);
                    startActivity(f);
                }
            });





        //recyclerview
        ArrayList<Items> aList = new ArrayList<>();
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));
        aList.add(new Items("Last Checked","Next Check","Replace"));




        mRecyclerView = findViewById(R.id.recycid);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ItemAdapter(aList,this);

        mRecyclerView.setLayoutManager(mLayoutManager);
      //  mRecyclerView.setAdapter(mAdapter);


    }

   @Override
    protected void onStart() {
       super.onStart();

       FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>
               (Blog.class, R.layout.items, BlogViewHolder.class, myref) {
           @Override
           protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

              viewHolder.setDate(model.getDate());
               viewHolder.setNext_Check(model.getNext_Check());
               viewHolder.setReplace(model.getReplace());
               viewHolder.setService(model.getService());


           }
       };
       mRecyclerView.setAdapter(firebaseRecyclerAdapter);
   }

   public static class BlogViewHolder extends RecyclerView.ViewHolder
   {



       View mview;
       public BlogViewHolder(View itemView)
       {
        super(itemView);
        mview = itemView;
       }

       public void setDate(String Date)
       {
           TextView lcheck = mview.findViewById(R.id.txtv3);
           lcheck.setText(Date);
       }
       public void setNext_Check(String Next_Check)
       {
           TextView ncheck = mview.findViewById(R.id.txtv5);
           ncheck.setText(Next_Check);
       }
       public void setReplace(String Replace)
       {
           TextView rep = mview.findViewById(R.id.txtv8);
           rep.setText(Replace);
       }

       public void setService(String Service)
       {
           TextView ser = mview.findViewById(R.id.txtv1);
           ser.setText(Service);
       }

   }

   }





