package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RatingActivity extends AppCompatActivity {

    private RatingBar RatingBar;
    private TextView RatingScale;

    private Button SendFeedback;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private EditText feedback;
    private String cid,key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        firebaseAuth= FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();

        }

       // databaseReference= FirebaseDatabase.getInstance().getReference("history");
        FirebaseUser user=firebaseAuth.getCurrentUser();

        final RatingBar RatingBar = (RatingBar) findViewById(R.id.ratingBar);
        final TextView RatingScale = (TextView) findViewById(R.id.RatingScale);
        final EditText Feedback = (EditText) findViewById(R.id.feedback);
        Button SendFeedback = (Button) findViewById(R.id.btn_Submit);


        cid = user.getUid();

        final Query query=FirebaseDatabase.getInstance().getReference().child("booking").orderByChild("UserId")
                .equalTo(cid).limitToLast(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    key=ds.getKey();
                    databaseReference=FirebaseDatabase.getInstance().getReference().child("booking").child(key);

                   // Toast.makeText(getApplicationContext(), key, Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       // databaseReference=FirebaseDatabase.getInstance().getReference().child("booking").child(key);

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
                HashMap userMap = new HashMap();
                userMap.put("feedback", feed);
                userMap.put("rating",num);
                databaseReference.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Thank you for sharing your feedback", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RatingActivity.this, MainActivity.class));
                            finish();
                        } else {
                            String message = task.getException().getMessage();
                            Toast.makeText(RatingActivity.this, "Error occurred!" + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}
