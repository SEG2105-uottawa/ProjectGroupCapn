package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ManageComplaintsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewComplaints;
    private ComplaintList complaintList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_complaints);
        
        getComplaintsFromDatabase();

        recyclerViewComplaints = findViewById(R.id.recyclerViewComplaints);
        recyclerViewComplaints.setAdapter(complaintList);
    }

    private void getComplaintsFromDatabase() {
    }
}