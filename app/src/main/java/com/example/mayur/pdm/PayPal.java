package com.example.mayur.pdm;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mayur.pdm.Config.Config;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class PayPal extends AppCompatActivity {

    Button palpay;
    EditText charge;

    private static final int PAYPAL_REQUEST_CODE=7171;
    private static PayPalConfiguration cofig=new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(Config.PAYPAL_CLIENT_ID);

    String amount="";

    protected void onDestroy(){
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal);

        String servtype=getIntent().getStringExtra("service");
        String scgarge=getIntent().getStringExtra("price");
        String dates=getIntent().getStringExtra("date");
        String tslot=getIntent().getStringExtra("timeslot");
        String regino=getIntent().getStringExtra("regnum");
        String make=getIntent().getStringExtra("model");

        Intent intent=new Intent(this,PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,cofig);
        startService(intent);

        palpay=(Button)findViewById(R.id.palpay);
        charge=(EditText) findViewById(R.id.charge);
        charge.setText(scgarge);

        palpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });
    }

    private void processPayment() {

        amount=charge.getText().toString();
        PayPalPayment payPalPayment=new PayPalPayment(new BigDecimal(String.valueOf(amount)),"RS",
                "Donate for Service",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent=new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,cofig);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(intent,PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            PaymentConfirmation confirmation=data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if(confirmation != null)
            {
                try {
                    String paymentDetails=confirmation.toJSONObject().toString(4);
                    startActivity(new Intent(this,PaymentDetails.class)
                            .putExtra("PaymentDetails",paymentDetails)
                            .putExtra("PaymentAmount",amount)
                    );
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

        }
        else if(resultCode== Activity.RESULT_CANCELED)
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show();
        else if(resultCode==PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this,"Invalid",Toast.LENGTH_SHORT).show();
    }
}
