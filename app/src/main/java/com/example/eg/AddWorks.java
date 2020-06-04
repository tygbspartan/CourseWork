package com.example.eg;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddWorks extends AppCompatActivity {

    private EditText workTitle, workDesc, Loc, Cont;
    private Spinner prefSalary;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_works);

        workTitle = findViewById(R.id.title_edit);
        workDesc = findViewById(R.id.desc_edit);
        Loc = findViewById(R.id.location_edit);
        Cont = findViewById(R.id.contact_edit);
        prefSalary = findViewById(R.id.salarySpinner);

        databaseReference = FirebaseDatabase.getInstance().getReference("Job");

        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.add_work_id);

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

    public void add_work(View view) {

        String work_Title = workTitle.getText().toString();
        String work_desc = workDesc.getText().toString();
        String work_loc = Loc.getText().toString();
        String work_cont = Cont.getText().toString();
        String salary = prefSalary.getSelectedItem().toString();

        if(TextUtils.isEmpty(work_Title)){
            Toast.makeText(this, "Work Title cant be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(work_cont)){
            Toast.makeText(this, "Contact cant be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            String id = databaseReference.push().getKey();
            Job workObj = new Job(id,work_Title,work_desc,work_loc,work_cont,salary);
            databaseReference.child(id).setValue(workObj);
            Toast.makeText(this, "Your work has been added.", Toast.LENGTH_SHORT).show();
        }

    }
}
