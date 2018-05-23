package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Book extends AppCompatActivity implements View.OnClickListener {

private CardView book_card,offers_card,contactus_card;

    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        mtoolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tbr_booking_menu);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Booking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mtoolbar.setTitleTextColor(getResources().getColor(android.R.color.white));



        book_card=(CardView)findViewById(R.id.add_booking);
        offers_card=(CardView)findViewById(R.id.add_offer);
        contactus_card=(CardView)findViewById(R.id.contact);

        book_card.setOnClickListener(this);
        offers_card.setOnClickListener(this);
        contactus_card.setOnClickListener(this);

       // bookid=findViewById(R.id.c);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.add_booking:i=new Intent(this,UIforBooking.class);startActivity(i);break;
            case R.id.add_offer:i=new Intent(this,uviewoffers.class);startActivity(i);break;
            case R.id.contact:i=new Intent(this,Contact_us.class);startActivity(i);break;
            default:break;
        }

    }
}
