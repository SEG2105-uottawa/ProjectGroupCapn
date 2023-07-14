package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TutorEngagementsActivity extends AppCompatActivity {

    private Tutor loggedInTutor;
    private RecyclerView recyclerViewAcceptedLessonRequests;
    private RecyclerView recyclerViewPendingLessonRequests;
    private PurchaseList adapterForRecyclerViewPendingLessonRequests;
    private TutorLessonList adapterForRecyclerViewAcceptedLessonRequests;
    private List<Purchase> listOfPendingPurchases;
    private List<Lesson> listOfAcceptedLessonRequests;
    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
    DatabaseReference purchasesRef = FirebaseDatabase.getInstance().getReference().child("purchases");
    DatabaseReference topicsRef = FirebaseDatabase.getInstance().getReference().child("topics");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_engagements);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("Tutor")){
            loggedInTutor = (Tutor) bundle.getSerializable("Tutor");
        }


        DatabaseReference loggedInTutorRef = usersRef.child(loggedInTutor.getDataBaseID());
        loggedInTutorRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loggedInTutor = snapshot.getValue(Tutor.class);
                //adapterForRecyclerViewAcceptedLessonRequests.notifyDataSetChanged();
                //adapterForRecyclerViewPendingLessonRequests.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        recyclerViewAcceptedLessonRequests = findViewById(R.id.recyclerViewAcceptedLessonRequests);
        recyclerViewPendingLessonRequests = findViewById(R.id.recyclerViewPendingLessonRequests);


        listOfPendingPurchases = loggedInTutor.getTopicPurchases();
        listOfAcceptedLessonRequests = loggedInTutor.getLessonPurchases();


        adapterForRecyclerViewPendingLessonRequests = new PurchaseList(listOfPendingPurchases);
        adapterForRecyclerViewAcceptedLessonRequests = new TutorLessonList(listOfAcceptedLessonRequests);


        recyclerViewPendingLessonRequests.setLayoutManager(new LinearLayoutManager(TutorEngagementsActivity.this));
        recyclerViewPendingLessonRequests.setAdapter(adapterForRecyclerViewPendingLessonRequests);


        recyclerViewAcceptedLessonRequests.setLayoutManager(new LinearLayoutManager(TutorEngagementsActivity.this));
        recyclerViewAcceptedLessonRequests.setAdapter(adapterForRecyclerViewAcceptedLessonRequests);

    }

    public void approvePurchase(Purchase purchase) {




    }

    public void rejectPurchase(Purchase purchase) {
    }
}
