package com.example.mayur.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class FAQs extends AppCompatActivity {

    ExpandableTextView expandableTextView;
    String longText1 = "What i need to use this application?\n" +
            "\t* you need android(new) mobile with internet service.\n" +
            "\t* Need a mail address for sign to this";
    String longText2 = "Am i need to pay for this application?\n" +
            "\t* No, This app free for all users.";
   // String longText3 = "";
    //String longText4 = "";
    //String longText5 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        expandableTextView = (ExpandableTextView)findViewById(R.id.expandable_text_view);
        expandableTextView.setText(longText1);

        //expandableTextView = (ExpandableTextView)findViewById(R.id.expandable_text_view1);
      //  expandableTextView.setText(longText2);


    }
}
