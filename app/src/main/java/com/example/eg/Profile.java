package com.example.eg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mFirebaseAuth = FirebaseAuth.getInstance();

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
}
