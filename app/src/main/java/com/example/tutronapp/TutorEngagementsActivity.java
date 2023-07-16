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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TutorEngagementsActivity extends AppCompatActivity {

    // Initialize
    private Tutor loggedInTutor;
    //TODO what is the type of acceptedPurchasesList
    private List<String> acceptedPurchasesList;
    private List<Purchase> pendingPurchasesList;

    private RecyclerView recyclerViewPending;
    private PurchaseList pendingListAdapter;

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

        pendingPurchasesList = loggedInTutor.getTopicPurchases();

        //For pending
        recyclerViewPending = findViewById(R.id.recyclerViewPendingLessonRequests);
        pendingListAdapter = new PurchaseList(pendingPurchasesList);
        recyclerViewPending.setLayoutManager(new LinearLayoutManager(TutorEngagementsActivity.this));
        recyclerViewPending.setAdapter(pendingListAdapter);

        // For accepted
        TextView textViewAccepted = findViewById(R.id.textViewAcceptedLessonRequests);
        textViewAccepted.setMovementMethod(new ScrollingMovementMethod());
        StringBuilder acceptedBuilder = new StringBuilder();

        DatabaseReference loggedInTutorRef = usersRef.child(loggedInTutor.getDataBaseID());

        loggedInTutorRef.child("topicPurchases").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                acceptedPurchasesList.clear();
                pendingPurchasesList.clear();

                for (DataSnapshot purchaseSnapshot : snapshot.getChildren()) {
                    try {
                        Purchase purchase = purchaseSnapshot.getValue(Purchase.class);
                        Log.d("FirebaseDatabase", "Snapshot: " + purchase);

                        if (purchase.isTutorApproved()) {
                            String lessonString = purchase.getTopicName() +
                                    " - " + purchase.getDateForLesson() +
                                    " - " + purchase.getStudentName();
                            acceptedPurchasesList.add(lessonString);
                        }
                        else if (!purchase.isTutorRejected()) {
                            //TODO not sure is it pendingPurchasesList.add is fine
                            pendingPurchasesList.add(purchase);
                        }
                    }
                    catch (Exception e) {
                        Log.e ("FirebaseError", "Error looping the purchase: " + e.getMessage());
                    }
                }

                pendingListAdapter.notifyDataSetChanged();
                updateUIAccepted();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e ("FirebaseError", "DatabaseError: " + error.getMessage());
            }
        });

        /*loggedInTutorRef.addValueEventListener(new ValueEventListener() {
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

         */

    }

    private void updateUIAccepted() {
        TextView textViewAccepted = findViewById(R.id.textViewAcceptedLessonRequests);

        StringBuilder stringBuilder = new StringBuilder();
        for (String lessonString : acceptedPurchasesList) {
            stringBuilder.append(lessonString).append("\n");
        }

        textViewAccepted.setText(stringBuilder.toString());
    }

    private void handleData() {
        DatabaseReference loggedInTutorRef = usersRef.child(loggedInTutor.getDataBaseID());
        loggedInTutorRef.child("topicPurchases").addListenerForSingleValueEvent(new ValueEventListener() {
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
        // Set tutorApproved flag to true
        purchase.setTutorApproved(true);

        String purchaseId = purchase.getDatabaseID();
        DatabaseReference purchaseRef = FirebaseDatabase.getInstance().getReference().child("purchases").child(purchaseId);
        purchaseRef.setValue(purchase);

        // Get the topicDatabaseID and studentDatabaseID from the purchase
        String topicDatabaseID = purchase.getLessonTaughtDatabaseID();
        String studentDatabaseID = purchase.getStudentPurchasingDatabaseID();

        // Reference to topics node
        DatabaseReference topicsRef = FirebaseDatabase.getInstance().getReference().child("topics");

        // Reference to users node
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        // Query for getting the Topic
        Query topicQuery = topicsRef.child(topicDatabaseID);
        topicQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Topic topic = dataSnapshot.getValue(Topic.class);
                Query studentQuery = usersRef.child(studentDatabaseID);
                studentQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Student student = dataSnapshot.getValue(Student.class);
                        Lesson lesson = new Lesson(topic, purchase.getDateForLesson(),loggedInTutor, studentDatabaseID, student.getName());
                        student.getPurchasedLessons().add(lesson);
                        student.getPendingPurchases().remove(purchase);
                        pendingPurchasesList.remove(purchase);
                        pendingListAdapter.notifyDataSetChanged();
                        loggedInTutor.getTopicPurchases().remove(purchase);

                        usersRef.child(studentDatabaseID).setValue(student);
                        usersRef.child(loggedInTutor.getDataBaseID()).setValue(loggedInTutor);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any error that may occur while fetching the topic
            }
        });



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
