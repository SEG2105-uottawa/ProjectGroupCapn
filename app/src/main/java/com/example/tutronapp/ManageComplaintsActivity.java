package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ManageComplaintsActivity extends AppCompatActivity implements ComplaintList.OnItemClickListener {

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
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(Complaint complaint) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View optionsView = LayoutInflater.from(this).inflate(R.layout.layout_complaint_options, null);
        builder.setView(optionsView);

        TextView textViewTitle = optionsView.findViewById(R.id.textViewTitle);
        TextView textViewContent = optionsView.findViewById(R.id.textViewContent);
        TextView textViewComplaintBy = optionsView.findViewById(R.id.textViewComplaintBy);
        TextView textViewAgainst = optionsView.findViewById(R.id.textViewAgainst);
        TextView textViewStatus = optionsView.findViewById(R.id.textViewStatus);
        Button btnSuspend = optionsView.findViewById(R.id.btnSuspend);
        Button btnDismiss = optionsView.findViewById(R.id.btnDismiss);
        Button btnClose = optionsView.findViewById(R.id.btnClose);

        AlertDialog dialog = builder.create();
        dialog.show();

        textViewTitle.setText(complaint.getComplaintTitle());
        textViewContent.setText(complaint.getContent());
        textViewComplaintBy.setText("Complaint By: " + complaint.getComplaintBy().getName());
        textViewAgainst.setText("Complaint Against: " + complaint.getComplaintAgainst().getName());
        textViewStatus.setText("Status: " + complaint.getStatus());

        // close button
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // suspend
        btnSuspend.setOnClickListener(v -> {
            // Suspend the complaint
            suspendComplaint(complaint, dialog);
        });

        // dismiss
        btnDismiss.setOnClickListener(v -> {
            // Dismiss the complain
           dismissComplaint(complaint, dialog);
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void suspendComplaint(Complaint complaint, AlertDialog dialog) {
        if (complaint != null) {
            AlertDialog.Builder suspendDialogBuilder = new AlertDialog.Builder(this);
            suspendDialogBuilder.setTitle("Suspend Complaint");
            suspendDialogBuilder.setView(R.layout.layout_suspend_options);

            suspendDialogBuilder.setPositiveButton("Suspend", (dialogInterface, i) -> {
                AlertDialog suspendDialog = (AlertDialog) dialogInterface;
                EditText editTextDuration = suspendDialog.findViewById(R.id.editTextDuration);

                if (editTextDuration != null) {
                    String durationStr = editTextDuration.getText().toString().trim();
                    if (!durationStr.isEmpty()) {
                        int duration = Integer.parseInt(durationStr);
                        // Suspend the complaint for the specified duration
                        suspendComplaintForDuration(complaint, duration, dialog);
                    } else {
                        Toast.makeText(ManageComplaintsActivity.this,
                                "Please fill the suspend date.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            suspendDialogBuilder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                // Close the dialog
                dialogInterface.dismiss();
            });

            AlertDialog suspendDialog = suspendDialogBuilder.create();
            suspendDialog.show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void suspendComplaintForDuration(Complaint complaint, int duration, AlertDialog dialog) {
        // TODO tutor should see a message informing them they have been suspended
        if (complaint == null) {
            return;
        }
        LocalDate suspensionEndDate = null;
        LocalDate finalSuspensionEndDate = suspensionEndDate;
        Long suspendedTillAsALongInMillis;

        if (duration == 0) {
            complaint.setStatus("suspended 0");
            suspendedTillAsALongInMillis = Long.valueOf(-1);

        }
        else {
            LocalDate currentDate = LocalDate.now();
            suspensionEndDate = currentDate.plusDays(duration);

            //max value of Long Storage
            if (duration < 106751991167.301){
                suspendedTillAsALongInMillis = System.currentTimeMillis() + (long) duration * 86400000;
            }
            else {
                suspendedTillAsALongInMillis = Long.valueOf(-1);
            }

            complaint.setStatus("suspended " + suspensionEndDate);
            complaint.getComplaintAgainst().setSuspensionEndDate(suspendedTillAsALongInMillis);
            


        }

        String complaintId = complaint.getDatabaseID();
        DatabaseReference complaintRef = complaints.child(complaintId);
        DatabaseReference tutorInQuestion = users.child(complaint.getComplaintAgainst().getDataBaseID());
        tutorInQuestion.setValue(complaint.getComplaintAgainst());

        LocalDate finalSuspensionEndDate1 = suspensionEndDate;
        complaintRef.setValue(complaint)
                .addOnSuccessListener(aVoid -> {
                    if (duration == 0) {
                        Toast.makeText(ManageComplaintsActivity.this,
                                "Complaint suspended for permanent", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ManageComplaintsActivity.this,
                                "Tutor suspended for " + duration + " days, until " +
                                        finalSuspensionEndDate1, Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ManageComplaintsActivity.this,
                            "Complaint suspension failed", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });
    }

    private void dismissComplaint(Complaint complaint, AlertDialog dialog) { // TESTED
        if (complaint != null) {
            complaint.setStatus("closed");
            String complaintId = complaint.getDatabaseID();
            DatabaseReference complaintRef = complaints.child(complaintId);

            complaintRef.setValue(complaint)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(ManageComplaintsActivity.this,
                                "Complaint dismissed successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(ManageComplaintsActivity.this,
                                "Complaint dismissal failed", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    });
        }
    }
}