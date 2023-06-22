package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageComplaintsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewComplaints;
    private ComplaintList complaintList;
    private DatabaseReference complaints = FirebaseDatabase.getInstance().getReference("complaints");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_complaints);
        recyclerViewComplaints = findViewById(R.id.recyclerViewComplaints);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewComplaints.setLayoutManager(layoutManager);

        complaints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Complaint> listComplaint = new ArrayList<>();
                for (DataSnapshot complaintSnapshot : dataSnapshot.getChildren()) {
                    Complaint complaint = complaintSnapshot.getValue(Complaint.class);
                    listComplaint.add(complaint);
                }
                complaintList = new ComplaintList(listComplaint);
                recyclerViewComplaints.setAdapter(complaintList);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

}