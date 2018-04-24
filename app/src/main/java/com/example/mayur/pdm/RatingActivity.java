package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RatingActivity extends AppCompatActivity {

    private RatingBar RatingBar;
    private TextView RatingScale;

    private Button SendFeedback;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private EditText feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        firebaseAuth= FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();

        }
        databaseReference= FirebaseDatabase.getInstance().getReference("history");
        FirebaseUser user=firebaseAuth.getCurrentUser();

        final RatingBar RatingBar = (RatingBar) findViewById(R.id.ratingBar);
        final TextView RatingScale = (TextView) findViewById(R.id.RatingScale);
        final EditText Feedback = (EditText) findViewById(R.id.feedback);
        Button SendFeedback = (Button) findViewById(R.id.btn_Submit);

        RatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                RatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        RatingScale.setText("Bad");
                        break;
                    case 2:
                        RatingScale.setText("Needs improvement");
                        break;
                    case 3:
                        RatingScale.setText("Good");
                        break;
                    case 4:
                        RatingScale.setText("Great");
                        break;
                    case 5:
                        RatingScale.setText("Excellent");
                        break;
                    default:
                        RatingScale.setText("");
                }
            }
        });

        SendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String feed=Feedback.getText().toString().trim();
                Float num=RatingBar.getRating();
                RatingInfo ratingInfo=new RatingInfo(feed,num);
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(num==null) {
                    Toast.makeText(RatingActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                }
                else {
                    databaseReference.child(user.getUid()).setValue(ratingInfo);
                }
                Intent intent = new Intent(RatingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(RatingActivity.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
