package com.example.mayur.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {

    private EditText pass, cpass;
    private Button btnChange;
    private FirebaseAuth auth;
    private Toolbar mToolbar;
    String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mToolbar=(Toolbar) findViewById(R.id.pw_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Change Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pass = (EditText) findViewById(R.id.password);
        cpass = (EditText) findViewById(R.id.cpassword);
        btnChange = (Button) findViewById(R.id.btn_changePW);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                auth = FirebaseAuth.getInstance();
                cid=auth.getCurrentUser().getUid();
                String password = pass.getText().toString().trim();
                String confirmPass = cpass.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals(confirmPass)) {
                    user.updatePassword(cpass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Password is updated, sign in with new password!", Toast.LENGTH_SHORT).show();
                                signOut();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Failed to update password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                else {
                    Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    private void signOut() {
        auth.signOut();
        startActivity(new Intent(ChangePassword.this, LoginActivity.class));
        finish();
    }
}
