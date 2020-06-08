package com.example.eg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private TextView profilename, profileemail, profilecontact, profilelocation;
    private Button profileedit;
    private ImageView picture;

    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        profilename = findViewById(R.id.profileName);
        profileemail = findViewById(R.id.profileEmail);
        profilecontact= findViewById(R.id.profileContact);
        profilelocation = findViewById(R.id.profileLocation);
        profileedit = findViewById(R.id.editProfile);
        picture = findViewById(R.id.pic);

        DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(mFirebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ViewProfile viewProfile = dataSnapshot.getValue(ViewProfile.class);
                profilename.setText(viewProfile.getpFullName());
                profileemail.setText(viewProfile.getpEmail());
                profilecontact.setText(viewProfile.getpContact());
                profilelocation.setText(viewProfile.getpLocation());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this, databaseError.getCode(),Toast.LENGTH_SHORT).show();
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

                        return true;
                }
                return false;
            }
        });
    }

    public void logout(View view) {
        mFirebaseAuth.signOut();
        Intent intent = new Intent(Profile.this,loginactivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    public void editProfile(View view) {
    }
}
