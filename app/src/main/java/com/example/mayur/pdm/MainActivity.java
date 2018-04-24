package com.example.mayur.pdm;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
   // private DrawerLayout emr,dul,ini,ham,upa,faq;
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mtoggle = new ActionBarDrawerToggle(this,mdrawerlayout,R.string.Open,R.string.Close);
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

    }




}
