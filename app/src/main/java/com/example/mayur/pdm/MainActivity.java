package com.example.mayur.pdm;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // private DrawerLayout emr,dul,ini,ham,upa,faq;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Navigation menu code
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.navigation_view);
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
                            case (R.id.service):
                                startActivity(new Intent(getApplicationContext(), RatingActivity.class));
                                break;
                            case (R.id.logout):
                                signOut();
                                return true;

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

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mtoggle = new ActionBarDrawerToggle(this,mdrawerlayout,R.string.Open,R.string.Close);
        mToolbar=(Toolbar) findViewById(R.id.nav_action) ;
        setSupportActionBar(mToolbar);

        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

       // NavigationView navigationView=(NavigationView)findViewById(R.id.drawer);
       // navigationView.setNavigationItemSelectedListener(this);



    }

//this is for btns
public void openActivity2(){
    Intent intent = new Intent(this, EmergencyServices.class);
    startActivity(intent);
}

public void openActivitychat(){
    Intent intent = new Intent(this,EmChat.class);
    startActivity(intent);

}


//this is for drawmenu to shortbtn close
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item){
        //handle navigation view item clicks here.
        int id = item.getItemId();

        //default go main
        if (id == R.id.EmSer){
            Intent go = new Intent(getApplicationContext(),EmergencyServices.class);
            startActivity(go);
        }
        if(id == R.id.Dul){
            Intent go = new Intent(getApplicationContext(),DulipPage.class);
            startActivity(go);
        }
        if(id == R.id.Ini){
            Intent go = new Intent(getApplicationContext(),InishaPage.class);
            startActivity(go);
        }
        if(id == R.id.nav_profile){
            Intent accountActivity = new Intent(getApplicationContext(), ViewProfileActivity.class);
            startActivity(accountActivity);
            return true;
        }
        if(id == R.id.promotion){
            Intent accountActivity = new Intent(getApplicationContext(), PromotionActivity.class);
            startActivity(accountActivity);
            return true;
        }
        if(id == R.id.service){
            Intent accountActivity = new Intent(getApplicationContext(), RatingActivity.class);
            startActivity(accountActivity);
            return true;
        }
        if(id == R.id.Upa){
            Intent go = new Intent(getApplicationContext(),UpadyaPage.class);
            startActivity(go);
        }
        if(id == R.id.faqs){
            Intent go = new Intent(getApplicationContext(),FAQs.class);
            startActivity(go);
        }
        if(id == R.id.logout){
            System.exit((0));
        }

        mdrawerlayout.closeDrawer(GravityCompat.START);
        return true;

    }*/


    }

    private void signOut() {
        auth.signOut();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
