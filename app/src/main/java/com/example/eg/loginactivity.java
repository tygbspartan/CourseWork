package com.example.eg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivity extends AppCompatActivity {

    private EditText login_email, login_pass;
    private FirebaseAuth mAuth;
    TextView registerhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        login_email = findViewById(R.id.login_email_id);
        login_pass = findViewById(R.id.login_pass_id);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        String email = login_email.getText().toString().trim();
        String pass = login_pass.getText().toString().trim();



        if (email.isEmpty()){
            Toast.makeText(this, "Email cant be empty", Toast.LENGTH_SHORT).show();
        }
        else if (pass.isEmpty()){
            Toast.makeText(this, "Password field is Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            userLogin(email,pass);
        }
    }

    private void userLogin(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(loginactivity.this, Works.class));
                            Toast.makeText(loginactivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(loginactivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                        }
                        // ...
                    }
                });
                }

    public void registerhere(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}

