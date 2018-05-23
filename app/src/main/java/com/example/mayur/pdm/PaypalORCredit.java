package com.example.mayur.pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaypalORCredit extends AppCompatActivity {
    Button paypal;
    Button card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal_orcredit);

        final String servtype=getIntent().getStringExtra("service");
        final String scgarge=getIntent().getStringExtra("price");
        final String dates=getIntent().getStringExtra("date");
        final String tslot=getIntent().getStringExtra("timeslot");
        final String regino=getIntent().getStringExtra("regnum");
        final String make=getIntent().getStringExtra("model");

        paypal=(Button)findViewById(R.id.paypal);
        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(PaypalORCredit.this,PayPal.class);
                intent.putExtra("service",servtype);
                intent.putExtra("price",scgarge);
                intent.putExtra("date",dates);
                intent.putExtra("timeslot",tslot);
                intent.putExtra("regnum",regino);
                intent.putExtra("model",make);
                startActivity(intent);

            }
        });

        card=(Button)findViewById(R.id.card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(PaypalORCredit.this,Payment_gateway.class);
                intent.putExtra("service",servtype);
                intent.putExtra("price",scgarge);
                intent.putExtra("date",dates);
                intent.putExtra("timeslot",tslot);
                intent.putExtra("regnum",regino);
                intent.putExtra("model",make);
                startActivity(intent);
            }
        });
    }
}
