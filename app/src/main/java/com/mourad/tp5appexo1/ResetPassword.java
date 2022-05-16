package com.mourad.tp5appexo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
Button btnra;
EditText emailUA, passwordUA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        btnra=(Button) findViewById(R.id.reintialiserbtnpass);
        emailUA=(EditText)findViewById(R.id.useremaila);
        passwordUA=(EditText)findViewById(R.id.olduserpassword);

        btnra.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = emailUA.getText().toString().trim();
                String password = passwordUA.getText().toString().trim();
                if (email.isEmpty()) {
                    emailUA.setError("email is required");
                    return;
                }
                if (password.isEmpty()) {
                    passwordUA.setError("password is required");
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
                                    Toast.makeText(ResetPassword.this, "mot de passe rénitialiser avec succes", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(ResetPassword.this, "Echec:"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    //}
                //}

            }
        });


    }
}
