package com.example.eg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
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
    private SearchView searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works);

        dbRef = FirebaseDatabase.getInstance().getReference("Job");

        lv = findViewById(R.id.lv_works);
        listWorks = new ArrayList<>();
        searchBar = findViewById(R.id.searchView);


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
                    listWorks.clear();
                for (DataSnapshot JobSnapshot : dataSnapshot.getChildren()) {
                    Job obj = JobSnapshot.getValue(Job.class);
                    listWorks.add(obj);
                }
                adapter = new WorksAdapter(Works.this, listWorks);

                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Works.this, "Your work could not be uploaded", Toast.LENGTH_SHORT).show();
            }
        });

        if(searchBar != null){
            searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String str) {
        List<Job> myList = new ArrayList<>();
        for(Job object : listWorks){
            if(object.getWorkTitle().toLowerCase().contains(str.toLowerCase()) || object.getLocation().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }
        }
        WorksAdapter adapter = new WorksAdapter(Works.this,myList);
        lv.setAdapter(adapter);
    }

}
