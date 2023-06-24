package com.example.tutronapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.example.tutronapp.ComplaintList;

public class ManageComplaintsActivity extends AppCompatActivity implements ComplaintList.OnItemClickListener{

    private RecyclerView recyclerViewComplaints;
    private ComplaintList complaintList;
    private DatabaseReference users = FirebaseDatabase.getInstance().getReference("users");
    private DatabaseReference complaints = FirebaseDatabase.getInstance().getReference("complaints");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_complaints);
        recyclerViewComplaints = findViewById(R.id.recyclerViewComplaints);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewComplaints.setLayoutManager(layoutManager);

        final ComplaintList.OnItemClickListener activity = (ComplaintList.OnItemClickListener) this;

        complaints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Complaint> listComplaint = new ArrayList<>();
                for (DataSnapshot complaintSnapshot : dataSnapshot.getChildren()) {
                    Complaint complaint = complaintSnapshot.getValue(Complaint.class);
                    if (complaint.getStatus().equals("open")) {
                        listComplaint.add(complaint);

                    }
                }
                complaintList = new ComplaintList(listComplaint, activity);
                recyclerViewComplaints.setAdapter(complaintList);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    // Handle item click
    @Override
    public void onItemClick(Complaint complaint) { // ITEM CLICK WORKS
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View optionsView = LayoutInflater.from(this).inflate(R.layout.layout_complaint_options, null);
        builder.setView(optionsView);

        Button btnSuspend = optionsView.findViewById(R.id.btnSuspend);
        Button btnDismiss = optionsView.findViewById(R.id.btnDismiss);

        AlertDialog dialog = builder.create();
        dialog.show();

        btnSuspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Suspend the complaint
                //TODO
                complaint.setSuspend(true);
                complaints.child(complaint.getDatabaseID()).setValue(complaint);
                Toast.makeText(ManageComplaintsActivity.this, "Complaint suspended",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the complaint
                complaints.child(complaint.getDatabaseID()).removeValue(); // NOT TESTED
                Toast.makeText(ManageComplaintsActivity.this, "Complaint dismissed", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

}