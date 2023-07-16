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

    private Tutor loggedInTutor;
    private List<String> acceptedLessonInfoList;
    private List<String> pendingLessonInfoList;

    private DatabaseReference usersRef;
    private DatabaseReference purchaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_engagements);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("Tutor")) {
            loggedInTutor = (Tutor) bundle.getSerializable("Tutor");
        }

        usersRef = FirebaseDatabase.getInstance().getReference().child("users");
        purchaseRef = FirebaseDatabase.getInstance().getReference().child("purchases");

        acceptedLessonInfoList = new ArrayList<>();
        pendingLessonInfoList = new ArrayList<>();

        handleData();

    }

    private void handleData() {
        DatabaseReference loggedInTutorRef = usersRef.child(loggedInTutor.getDataBaseID());
        loggedInTutorRef.child("topicPurchases").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                acceptedLessonInfoList.clear();
                pendingLessonInfoList.clear();

                Log.d("FirebaseSnapshot", "snapshot" + snapshot.getValue());
                for (DataSnapshot purchaseSnapshot : snapshot.getChildren()) {
                    try {
                        Purchase purchase = purchaseSnapshot.getValue(Purchase.class);
                        Log.d("FirebaseSnapshot", "purchaseSnapshot" + purchase);

                        if (purchase.isTutorApproved()) {
                            String lessonInfo = purchase.getTopicName()
                                    + " - " + purchase.getDateForLesson()
                                    + " - " + purchase.getStudentName();

                            acceptedLessonInfoList.add(lessonInfo);
                        } else {
                            String lessonInfo = purchase.getTopicName()
                                    + " - " + purchase.getDateForLesson()
                                    + " - " + purchase.getStudentName();

                            pendingLessonInfoList.add(lessonInfo);
                        }
                    } catch (Exception e) {
                        Log.e("FirebaseError", "Error parsing Purchase data: " + e.getMessage());
                    }
                }

                updateUIAccpetedLesson();
                updateUIPendingLesson();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Error reading topicPurchases: " + error.getMessage());
            }
        });
    }

    private void updateUIPendingLesson() {
        TextView textViewAcceptedLessonRequests = findViewById(R.id.textViewAcceptedLessonRequests);

        StringBuilder stringBuilder = new StringBuilder();
        for (String lessonInfo : acceptedLessonInfoList) {
            stringBuilder.append(lessonInfo).append("\n");
        }

        textViewAcceptedLessonRequests.setText(stringBuilder.toString());
    }

    private void updateUIAccpetedLesson() {
        TextView textViewPendingLessonRequests = findViewById(R.id.textViewPendingLessonRequests);

        StringBuilder stringBuilder = new StringBuilder();
        for (String lessonInfo : pendingLessonInfoList) {
            stringBuilder.append(lessonInfo).append("\n");
        }

        textViewPendingLessonRequests.setText(stringBuilder.toString());
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

        updateUIAccpetedLesson();
        updateUIPendingLesson();

        Toast.makeText(this, "Approved purchase successfully", Toast.LENGTH_LONG).show();
    }

    public void rejectPurchase(Purchase purchase) {
        purchase.setTutorRejected(true);

        String purchaseId = purchase.getDatabaseID();
        DatabaseReference purchaseRef =
                FirebaseDatabase.getInstance().getReference().child("purchases").child(purchaseId);
        purchaseRef.setValue(purchase);

        updateUIPendingLesson();

        Toast.makeText(this, "Rejected purchase successfully", Toast.LENGTH_LONG).show();
    }
}
