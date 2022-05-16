package com.mourad.tp5appexo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
Button btnc,btnr,btnl;
EditText emailU, passwordU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        btnc= (Button) findViewById(R.id.createbtn);
        btnr=(Button) findViewById(R.id.reintialiserbtn);
        btnl=(Button) findViewById(R.id.loginbtn);
        emailU=(EditText)findViewById(R.id.useremail);
        passwordU=(EditText)findViewById(R.id.userpassword);


        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailU.getText().toString().trim();
                String password = passwordU.getText().toString().trim();

                if (email.isEmpty()) {
                    emailU.setError("email is required");
                    return;
                }
                if (password.isEmpty()) {
                    passwordU.setError("password is required");
                    return;
                }
                if (password.length() < 6) {
                    passwordU.setError("password must be >= 6 characters");
                    return;
                }

                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AuthActivity.this, "Compte créer avec succes, " + "login pour continuer", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AuthActivity.this, "Echec:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailU.getText().toString().trim();
                String password=passwordU.getText().toString().trim();
                if (email.isEmpty()) {
                    emailU.setError("email is required");
                    return;
                }
                if (password.isEmpty()) {
                    passwordU.setError("password is required");
                    return;
                }
                /*if (npassword.isEmpty() && cnpassword.isEmpty()) {
                    NpasswordUA.setError("nv password and confirm password are required");
                    return;
                }*/
                // else{
                //  if(npassword==cnpassword){
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AuthActivity.this, "email envoyer avec succes", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(AuthActivity.this, "Echec:"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //}
                //}

            }
        });

        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailU.getText().toString().trim();
                String password=passwordU.getText().toString().trim();

                if (email.isEmpty()) {
                    emailU.setError("email is required");
                    return;
                }
                if (password.isEmpty()) {
                    passwordU.setError("password is required");
                    return;
                }
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                       startActivity(new Intent(getApplicationContext(), GrilleProduitsActivity.class));
                    }

                });


            }
        });
    }




}
