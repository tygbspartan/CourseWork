package com.example.eg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText signup_email, signup_pass, signup_confirmpass, signup_fname, signup_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup_email = findViewById(R.id.signup_email_id);
        signup_pass = findViewById(R.id.signup_pass_id);
        signup_confirmpass = findViewById(R.id.signup_Confirmpass_id);
        signup_fname = findViewById(R.id.fullname_id);
        signup_contact = findViewById(R.id.phone_id);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        //updateUI(currentUser);
//    }

    public void signup(View view) {
        //signup button click garda yo call hunxa
        String name = signup_fname.getText().toString().trim();
        String contact = signup_contact.getText().toString().trim();
        String email = signup_email.getText().toString().trim();
        String pass = signup_pass.getText().toString().trim();
        String confirm_pass = signup_confirmpass.getText().toString().trim();


        if (pass.length() < 6){
            Toast.makeText(this, "Password Length Insuff..", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(pass.equals(confirm_pass)){
            //crieteria pass for signup user
            createAccount(email,pass);
        }
        else{
            Toast.makeText(this, "Password Dont Match", Toast.LENGTH_SHORT).show();
        }
    }

    private void createAccount(String email, String pass) {

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(MainActivity.this, loginactivity.class));
                            Toast.makeText(MainActivity.this, "Signup Success", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
