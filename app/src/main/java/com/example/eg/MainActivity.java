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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText signup_email, signup_pass, signup_confirmpass, signup_fname, signup_contact,signup_location;
    String name, contact, location, email,pass, confirm_pass;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup_email = findViewById(R.id.signup_email_id);
        signup_pass = findViewById(R.id.signup_pass_id);
        signup_confirmpass = findViewById(R.id.signup_Confirmpass_id);
        signup_fname = findViewById(R.id.fullname_id);
        signup_contact = findViewById(R.id.phone_id);
        signup_location = findViewById(R.id.location_id);

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
        name = signup_fname.getText().toString().trim();
        contact = signup_contact.getText().toString().trim();
        location = signup_location.getText().toString().trim();
        email = signup_email.getText().toString().trim();
        pass = signup_pass.getText().toString().trim();
        confirm_pass = signup_confirmpass.getText().toString().trim();


        if (pass.length() < 6){
            Toast.makeText(this, "Password Length Insuff..", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (name.isEmpty() || contact.isEmpty() || location.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm_pass.isEmpty()){
            Toast.makeText(this, "Fields should not be empty!!", Toast.LENGTH_SHORT).show();
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
                            sendUserData();
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

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users").child(mAuth.getCurrentUser().getUid());
        ViewProfile viewProfile = new ViewProfile(name,email,contact,location);
        databaseReference.setValue(viewProfile);
    }

    public void btnSignIn(View view) {
        Intent intent = new Intent(MainActivity.this,loginactivity.class);
        startActivity(intent);
    }
}
