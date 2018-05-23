package com.example.mayur.pdm;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mayur.pdm.SpareParts.views.SparePartsActivity;
import com.example.mayur.pdm.SpareParts.views.SparePartsAdminActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    // private DrawerLayout emr,dul,ini,ham,upa,faq;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private TextView mNameTextView;
    private TextView mEmailTextView;
    String cid;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //current user
        auth = FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("users");
        cid=auth.getCurrentUser().getUid();

        //Navigation menu code
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(cid.equals("KvxnhxfFVzTHXTtY9PZV9rqOTz43")){
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, findViewById(R.id.navigation_view));
            NavigationView navigationViewAdmin = findViewById(R.id.navigation_viewAdmin);
            navigationViewAdmin.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {

                            switch (menuItem.getItemId()) {
                                case (R.id.nav_promMan):
                                    Intent accountActivity = new Intent(getApplicationContext(), activity_promotion_admin.class);
                                    startActivity(accountActivity);
                                    break;

                                case (R.id.Emadchat):
                                    startActivity(new Intent(getApplicationContext(), EmChat.class));
                                    break;
                                case (R.id.RatingAdmin):
                                    startActivity(new Intent(getApplicationContext(), admin_rating.class));
                                    break;

                                case (R.id.SparePartAdmin):
                                    startActivity(new Intent(getApplicationContext(), SparePartsAdminActivity.class));
                                    break;

                                case (R.id.logout):
                                    signOut();
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    break;
                                case (R.id.bookings):
                                    startActivity(new Intent(getApplicationContext(),ListOFBookings.class));
                                    break;
                                case (R.id.noti):
                                    startActivity(new Intent(getApplicationContext(),SendingPushNotifications.class));
                                    break;
                                case (R.id.adse):
                                    startActivity(new Intent(getApplicationContext(),ServiceAdding.class));
                                    break;
                                //return true;

                            }
                            // set item as selected to persist highlight
//                        menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });

            View navHeaderView = navigationViewAdmin.getHeaderView(0);


            mEmailTextView = (TextView) navHeaderView.findViewById(R.id.textView_email);
            mNameTextView=(TextView) navHeaderView.findViewById(R.id.textView_name);

        }
        else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, findViewById(R.id.navigation_viewAdmin));
            navigationView = findViewById(R.id.navigation_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case (R.id.nav_profile):
                                Intent accountActivity = new Intent(getApplicationContext(), ViewProfileActivity.class);
                                startActivity(accountActivity);
                                return true;
                            case (R.id.promotion):
                                startActivity(new Intent(MainActivity.this, PromotionActivity.class));
                                return true;
                            case (R.id.Dul):
                                startActivity(new Intent(getApplicationContext(), Fueldul.class));
                                return true;
                            case (R.id.service):
                                startActivity(new Intent(getApplicationContext(), bhistory.class));
                                break;
                            case (R.id.Upasp):
                                startActivity(new Intent(getApplicationContext(), SparePartsActivity.class));
                                break;
                            case (R.id.EmSer):
                                startActivity(new Intent(getApplicationContext(), EmergencyServices.class));
                                break;
                            case (R.id.logout):
                                signOut();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                break;
                            case (R.id.faqs):
                                startActivity(new Intent(getApplicationContext(), FAQs.class));
                                break;
                            case (R.id.book):
                                startActivity(new Intent(getApplicationContext(), Book.class));
                                break;
                            case (R.id.rating):
                                startActivity(new Intent(getApplicationContext(), RatingActivity.class));
                                break;


                                //return true;

                            }
                            // set item as selected to persist highlight
//                        menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });

            View navHeaderView = navigationView.getHeaderView(0);

            mEmailTextView = (TextView) navHeaderView.findViewById(R.id.textView_email);
            mNameTextView=(TextView) navHeaderView.findViewById(R.id.textView_name);
        }





        databaseReference.child(cid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name=dataSnapshot.child("fname").getValue().toString();
                    mNameTextView.setText(name);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //get current user and set email address
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();

        }else {
            checkUserExist();
            mEmailTextView.setText(user.getEmail());
            // setDataToView(user);

        }

    }

    private void signOut() {
        auth.signOut();

    }
    private void checkUserExist() {
        final String current_uid=auth.getCurrentUser().getUid();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(current_uid))
                {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    finish();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
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