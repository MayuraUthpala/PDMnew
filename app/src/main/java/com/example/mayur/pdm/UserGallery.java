package com.example.mayur.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserGallery extends AppCompatActivity {

    private RecyclerView mrecyclerview;
    private ImageAdapter madapter;

    private ProgressBar mpbar;

    private DatabaseReference daref;
    private List<Upload> mUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_gallery);

        mrecyclerview = findViewById(R.id.re_gal);
        mrecyclerview.setHasFixedSize(true);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mpbar = findViewById(R.id.pb);

        mUpload = new ArrayList<>();

        daref = FirebaseDatabase.getInstance().getReference("uploads");

        daref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Upload upload = ds.getValue(Upload.class);
                    mUpload.add(upload);
                }
                madapter = new ImageAdapter(UserGallery.this, mUpload);
                mrecyclerview.setAdapter(madapter);
                mpbar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserGallery.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mpbar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
