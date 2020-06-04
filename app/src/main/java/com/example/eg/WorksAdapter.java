package com.example.eg;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WorksAdapter extends ArrayAdapter<Job> {

    private Activity context;
    private List<Job> workList;


    public WorksAdapter(Activity context, List<Job> workList ) {
        super(context, R.layout.item_layout, workList);
        this.context = context;
        this.workList = workList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_layout, null, true);

        TextView tvTitle = view.findViewById(R.id.textTitle);
        TextView tvDesc = view.findViewById(R.id.textDesc);
        TextView tvLocation = view.findViewById(R.id.textLoc);
        TextView tvContact = view.findViewById(R.id.textContact);
        TextView tvSalary = view.findViewById(R.id.textSalary);

        Job jobObj = workList.get(position);
        tvTitle.setText(jobObj.getWorkTitle());
        tvDesc.setText(jobObj.getWorkDesc());
        tvLocation.setText(jobObj.getLocation());
        tvContact.setText(jobObj.getContact());
        tvSalary.setText(jobObj.getSalary());
//        tvContact.setText("Contact");
//        tvSalary.setText("Salary");

        return view;
    }
}
