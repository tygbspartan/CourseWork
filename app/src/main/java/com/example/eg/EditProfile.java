package com.example.eg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {

    private EditText newFullName, newEmail, newContact, newLocation;
    private Button edit;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        newFullName = findViewById(R.id.etNameUpdate);
        newEmail = findViewById(R.id.etEmailUpdate);
        newContact = findViewById(R.id.etContactUpdate);
        newLocation = findViewById(R.id.etLocationUpdate);
        edit = findViewById(R.id.btnSave);

        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(mFirebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ViewProfile viewProfile = dataSnapshot.getValue(ViewProfile.class);
                newFullName.setText(viewProfile.getpFullName());
                newEmail.setText(viewProfile.getpEmail());
                newContact.setText(viewProfile.getpContact());
                newLocation.setText(viewProfile.getpLocation());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EditProfile.this, databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = newFullName.getText().toString();
                String email = newEmail.getText().toString();
                String contact = newContact.getText().toString();
                String location = newLocation.getText().toString();

                ViewProfile viewProfile = new ViewProfile(name,email,contact,location);
                databaseReference.setValue(viewProfile);

                finish();
            }
        });


        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.profile_id);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.works_id:
                        startActivity(new Intent(getApplicationContext(),Works.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.add_work_id:
                        startActivity(new Intent(getApplicationContext(),AddWorks.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile_id:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}
