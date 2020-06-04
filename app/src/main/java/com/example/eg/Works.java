package com.example.eg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Works extends AppCompatActivity {

    private ListView lv;
    private DatabaseReference dbRef;
    private List<Job> listWorks;
    WorksAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works);

        dbRef = FirebaseDatabase.getInstance().getReference("Job");

        lv = findViewById(R.id.lv_works);
        listWorks = new ArrayList<>();


        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.works_id);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.works_id:

                        return true;
                    case R.id.add_work_id:
                        startActivity(new Intent(getApplicationContext(), AddWorks.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile_id:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }

    //onstart


    @Override
    protected void onStart() {
        super.onStart();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot JobSnapshot : dataSnapshot.getChildren()) {
                    Job obj = JobSnapshot.getValue(Job.class);
                    listWorks.add(obj);
                }
                adapter = new WorksAdapter(Works.this, listWorks);
//                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);

                Toast.makeText(Works.this, "Your work has been added.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Works.this, "Your work could not be uploaded", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
