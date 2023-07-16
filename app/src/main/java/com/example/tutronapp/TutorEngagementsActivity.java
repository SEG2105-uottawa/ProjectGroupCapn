package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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
        // TODO It is not finished
        /**
        for (Purchase purchase : loggedInTutor.getTopicPurchases()) {

            if (purchase.isTutorApproved()) {
                Lesson acceptedLesson = new Lesson();
                acceptedLesson.setDatabaseID(purchase.getLessonTaughtDatabaseID());

                Topic topicToBeTaught = new Topic();
                topicToBeTaught.setTitle(purchase.getTopicName());

                acceptedLesson.setTopicToBeTaught(topicToBeTaught);

                acceptedLesson.setDateOfLesson(purchase.getDateForLesson());

                Tutor tutorTeaching = new Tutor();

                acceptedLesson.setTutorTeaching(tutorTeaching);

                acceptedLesson.setStudentDatabaseID(purchase.getStudentPurchasingDatabaseID());
                acceptedLesson.setStudentName(purchase.getStudentName());


                listOfAcceptedLessonRequests.add(acceptedLesson);
            }
        }

        // Notify the adapter about the data change
        adapterForRecyclerViewAcceptedLessonRequests.notifyDataSetChanged();
        **/

        //processAcceptedPurchases();

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
    }

    private void processAcceptedPurchases() {
        //TODO sth like in approvePurchase
        listOfAcceptedLessonRequests.clear();

        for (Purchase purchase : loggedInTutor.getTopicPurchases()) {
            if (purchase.isTutorApproved()) {
                Lesson acceptedLesson = new Lesson();
                acceptedLesson.setDatabaseID(purchase.getLessonTaughtDatabaseID());

                Topic topicToBeTaught = new Topic();
                topicToBeTaught.setTitle(purchase.getTopicName());

                acceptedLesson.setTopicToBeTaught(topicToBeTaught);

                acceptedLesson.setDateOfLesson(purchase.getDateForLesson());

                Tutor tutorTeaching = new Tutor();

                acceptedLesson.setTutorTeaching(tutorTeaching);

                acceptedLesson.setStudentDatabaseID(purchase.getStudentPurchasingDatabaseID());
                acceptedLesson.setStudentName(purchase.getStudentName());


                listOfAcceptedLessonRequests.add(acceptedLesson);
            }
        }

        // Notify the adapter about the data change
        adapterForRecyclerViewAcceptedLessonRequests.notifyDataSetChanged();

    }

    public void approvePurchase(Purchase purchase) {
        purchase.setTutorApproved(true);

        Lesson acceptedLesson = new Lesson();
        acceptedLesson.setDatabaseID(purchase.getLessonTaughtDatabaseID());

        Topic topicToBeTaught = new Topic();
        topicToBeTaught.setTitle(purchase.getTopicName());

        acceptedLesson.setTopicToBeTaught(topicToBeTaught);

        acceptedLesson.setDateOfLesson(purchase.getDateForLesson());

        Tutor tutorTeaching = new Tutor();

        acceptedLesson.setTutorTeaching(tutorTeaching);

        acceptedLesson.setStudentDatabaseID(purchase.getStudentPurchasingDatabaseID());
        acceptedLesson.setStudentName(purchase.getStudentName());


        listOfAcceptedLessonRequests.add(acceptedLesson);


        adapterForRecyclerViewAcceptedLessonRequests.notifyDataSetChanged();


        String purchaseId = purchase.getDatabaseID();
        DatabaseReference purchaseRef =
                FirebaseDatabase.getInstance().getReference().child("purchases").child(purchaseId);
        purchaseRef.setValue(purchase);

        Toast.makeText(this, "Approved purchase successfully", Toast.LENGTH_LONG).show();
    }

    public void rejectPurchase(Purchase purchase) {
        purchase.setTutorRejected(true);

        String purchaseId = purchase.getDatabaseID();
        DatabaseReference purchaseRef =
                FirebaseDatabase.getInstance().getReference().child("purchases").child(purchaseId);
        purchaseRef.setValue(purchase);

        Toast.makeText(this, "Rejected purchase successfully", Toast.LENGTH_LONG).show();
    }
}
