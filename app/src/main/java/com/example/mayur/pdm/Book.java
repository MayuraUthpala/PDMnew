package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class Book extends AppCompatActivity implements View.OnClickListener {

private CardView book_card,offers_card;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);



        book_card=(CardView)findViewById(R.id.add_booking);
        offers_card=(CardView)findViewById(R.id.add_offer);

        book_card.setOnClickListener(this);
        offers_card.setOnClickListener(this);

       // bookid=findViewById(R.id.c);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.add_booking:i=new Intent(this,chooseService.class);startActivity(i);break;
            case R.id.add_offer:i=new Intent(this,Offers.class);startActivity(i);break;
            default:break;
        }

    }
}
