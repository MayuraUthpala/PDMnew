package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    //objects
    private DatabaseReference databaseReference;
    private EditText FirstName, Address, LastName, Mobile;
    private Button buttonAdd;
    private FirebaseAuth firebaseAuth;
    String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUserID= firebaseAuth.getCurrentUser().getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users").child(currentUserID);

/*        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();

        }*/
       // databaseReference= FirebaseDatabase.getInstance().getReference("users");
        FirstName= (EditText)findViewById(R.id.FirstName);
        LastName= (EditText)findViewById(R.id.LastName);
        Address= (EditText)findViewById(R.id.Address);
        Mobile=(EditText) findViewById(R.id.Number);
        buttonAdd = (Button) findViewById(R.id.btn_addProfile);



        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveProfile();



                /*if(num.equals("")){
                    Mobile.setError("Mobile required");
                    Mobile.requestFocus();
                    return;
                }*/

            }
        });

    }

    private void saveProfile() {
        String fname = FirstName.getText().toString().trim();
        String lname = LastName.getText().toString().trim();
        String add = Address.getText().toString().trim();
        String num = Mobile.getText().toString().trim();
        //  Integer num=Integer.parseInt(Mobile.getText().toString());

        if (TextUtils.isEmpty(fname)) {
            Toast.makeText(ProfileActivity.this, "Enter first name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(lname)) {
            Toast.makeText(ProfileActivity.this, "Enter Last Name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(add)) {
            Toast.makeText(ProfileActivity.this, "Address Required!", Toast.LENGTH_SHORT).show();
            return;
        }
        int numT = 0;
        if (num.isEmpty()) {
            Toast.makeText(ProfileActivity.this, "Enter Phone number!", Toast.LENGTH_SHORT).show();
        }
        if (num.length() !=9 ) {
            Toast.makeText(getApplicationContext(), "Incorrect Phone number length!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!num.isEmpty()){
            try {
                numT = Integer.parseInt(num);
                HashMap userMap = new HashMap();
                userMap.put("address", add);
                userMap.put("fname", fname);
                userMap.put("lname", lname);
                userMap.put("phone", numT);
                databaseReference.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Saved successfully!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                            finish();
                        } else {
                            String message = task.getException().getMessage();
                            Toast.makeText(ProfileActivity.this, "Error occurred!" + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (NumberFormatException e) {
                Toast.makeText(ProfileActivity.this, "Enter Phone number!", Toast.LENGTH_SHORT).show();
            }
        }





            /*ProfileInformation profileInformation = new ProfileInformation(fname, lname, add, num);
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).setValue(profileInformation);
            Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finish();*/

    }


}
