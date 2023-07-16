package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TutorEngagementsActivity extends AppCompatActivity {

    // Initialize
    private Tutor loggedInTutor;
    //TODO what is the type of acceptedPurchasesList
    private List<Lesson> acceptedPurchasesList;
    private List<Purchase> pendingPurchasesList;

    private RecyclerView recyclerViewPending;
    private RecyclerView recyclerViewAccepted;

    private PurchaseList pendingListAdapter;
    private TutorLessonList acceptedListAdapter;

    // Database
    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
    DatabaseReference purchasesRef = FirebaseDatabase.getInstance().getReference().child("purchases");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_engagements);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("Tutor")) {
            loggedInTutor = (Tutor) bundle.getSerializable("Tutor");
        }

        // Initialize for null exception
        pendingPurchasesList = new ArrayList<>();
        acceptedPurchasesList = new ArrayList<>();

        //For pending
        recyclerViewPending = findViewById(R.id.recyclerViewPendingLessonRequests);
        pendingListAdapter = new PurchaseList(pendingPurchasesList);
        recyclerViewPending.setLayoutManager(new LinearLayoutManager(TutorEngagementsActivity.this));
        recyclerViewPending.setAdapter(pendingListAdapter);

        // For accepted
        recyclerViewAccepted = findViewById(R.id.recyclerViewAcceptedLessonRequests);
        // TODO acceptedPurchaseList?
        acceptedPurchasesList = loggedInTutor.getLessonPurchases();
        // TODO acceptedListAdapter
        recyclerViewAccepted.setLayoutManager(new LinearLayoutManager(TutorEngagementsActivity.this));
        recyclerViewAccepted.setAdapter(acceptedListAdapter);

        DatabaseReference loggedInTutorRef = usersRef.child(loggedInTutor.getDataBaseID());

        loggedInTutorRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loggedInTutor = snapshot.getValue(Tutor.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "DatabaseError" + error);
            }
        });

        handleData();

    }

    private void handleData() {
        DatabaseReference loggedInTutorRef = usersRef.child(loggedInTutor.getDataBaseID());
        loggedInTutorRef.child("topicPurchases").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // acceptedPurchasesList.clear();
                pendingPurchasesList.clear();

                for (DataSnapshot purchaseSnapshot : snapshot.getChildren()) {
                    try {
                        Purchase purchase = purchaseSnapshot.getValue(Purchase.class);
                        Log.d("FirebaseDatabase", "Snapshot: " + purchase);

                        if (purchase.isTutorApproved()) {
                            // acceptedPurchasesList.add(purchase);
                        } else if (!purchase.isTutorRejected()) {
                            pendingPurchasesList.remove(purchase);
                        }
                    } catch (Exception e) {
                        Log.e("FirebaseError", "Error parsing Purchase data: " + e.getMessage());
                    }
                }

                pendingListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Error reading topicPurchases: " + error.getMessage());
            }
        });
    }


    public void approvePurchase(Purchase purchase) {
        purchase.setTutorApproved(true);

        String purchaseId = purchase.getDatabaseID();
        DatabaseReference purchaseRef =
                FirebaseDatabase.getInstance().getReference().child("purchases").child(purchaseId);
        purchaseRef.setValue(purchase);

        DatabaseReference userTopicPurchasesRef =
                usersRef.child(loggedInTutor.getDataBaseID()).child("topicPurchases").child(purchaseId);
        userTopicPurchasesRef.setValue(purchase);

        // acceptedPurchasesList.add(purchase);
        pendingPurchasesList.remove(purchase);

        pendingListAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Approved purchase successfully", Toast.LENGTH_LONG).show();
    }

    public void rejectPurchase(Purchase purchase) {
        purchase.setTutorRejected(true);

        String purchaseId = purchase.getDatabaseID();
        DatabaseReference purchaseRef =
                FirebaseDatabase.getInstance().getReference().child("purchases").child(purchaseId);
        purchaseRef.setValue(purchase);

        DatabaseReference userTopicPurchasesRef =
                usersRef.child(loggedInTutor.getDataBaseID()).child("topicPurchases").child(purchaseId);
        userTopicPurchasesRef.setValue(purchase);

        pendingPurchasesList.remove(purchase);

        Toast.makeText(this, "Rejected purchase successfully", Toast.LENGTH_LONG).show();
    }
}
