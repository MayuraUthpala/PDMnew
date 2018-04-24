package com.example.mayur.pdm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfileActivity extends AppCompatActivity {

    private TextView FirstName, Address, LastName, Mobile;
    private ProgressBar progressBar;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private Toolbar mToolbar;
    private Button changePW,btnRemoveUser;
    String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        /*final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            startActivity(new Intent(ViewProfileActivity.this, LoginActivity.class));
            finish();

        }*/
        mToolbar=(Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("User Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FirstName = (TextView) findViewById(R.id.vFirstName);
        LastName=(TextView) findViewById(R.id.vLastName);
        Address=(TextView) findViewById(R.id.vAddress);
        Mobile=(TextView) findViewById(R.id.vNumber);
        changePW=(Button) findViewById(R.id.btn_changePW) ;
        btnRemoveUser=(Button) findViewById(R.id.remove_user_button) ;

        auth = FirebaseAuth.getInstance();
        cid=auth.getCurrentUser().getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users").child(cid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String vfname=dataSnapshot.child("fname").getValue().toString();
                    String vlname=dataSnapshot.child("lname").getValue().toString();
                    String vaddress=dataSnapshot.child("address").getValue().toString();

                    String vphone=dataSnapshot.child("phone").getValue().toString();
                    FirstName.setText(vfname);
                    LastName.setText(vlname);
                    Address.setText(vaddress);
                    Mobile.setText(vphone);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProfileActivity.this, ChangePassword.class));
                finish();
            }
        });

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            startActivity(new Intent(ViewProfileActivity.this, LoginActivity.class));
            finish();

        }
        btnRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressBar.setVisibility(View.VISIBLE);
                if (user != null) {
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ViewProfileActivity.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ViewProfileActivity.this, SignupActivity.class));
                                        finish();
                                       // progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(ViewProfileActivity.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                       // progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }
            }
        });


    }

    FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(new Intent(ViewProfileActivity.this, LoginActivity.class));
                finish();
            }
        }


    };

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
