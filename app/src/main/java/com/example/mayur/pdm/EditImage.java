package com.example.mayur.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class EditImage extends AppCompatActivity implements ShowAdapter.OnItemClickListener{

    private RecyclerView mrecyclerview;
    private ShowAdapter madapter;

    private FirebaseStorage mstorage;
    private ProgressBar mpbar;

    private DatabaseReference daref;
    private ValueEventListener mdblistener;

    private List<Upload> mUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);

        mrecyclerview = findViewById(R.id.re_ga);
        mrecyclerview.setHasFixedSize(true);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mpbar = findViewById(R.id.pbg);

        mUpload = new ArrayList<>();
        madapter = new ShowAdapter(EditImage.this, mUpload);
        mrecyclerview.setAdapter(madapter);

        madapter.setOnItemClickListener(EditImage.this);

        mstorage = FirebaseStorage.getInstance();

        daref = FirebaseDatabase.getInstance().getReference("uploads");

        mdblistener =  daref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUpload.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Upload upload = ds.getValue(Upload.class);
                    upload.setkey(ds.getKey());
                    mUpload.add(upload);
                }
                madapter.notifyDataSetChanged();

                mpbar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EditImage.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mpbar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void WhateverClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

        Upload selectedItem = mUpload.get(position);
        final String selectedKey = selectedItem.getkey();

        StorageReference imref = mstorage.getReferenceFromUrl(selectedItem.getUrl());
        imref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                daref.child(selectedKey).removeValue();
                Toast.makeText(EditImage.this, "Successfully removed", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        daref.removeEventListener(mdblistener);
    }
}
