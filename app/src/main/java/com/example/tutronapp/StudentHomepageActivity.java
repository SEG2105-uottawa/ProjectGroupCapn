package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class StudentHomepageActivity extends AppCompatActivity {

    private Student loggedInStudent;
    private Button btnViewPurchaseStatus, btnPurchaseLesson;
    private RecyclerView recyclerViewYourLessons;
    private LessonList adapterForRecyclerViewYourLessons;
    private List<Lesson> listOfLessons;
    private List<Purchase> listOfPendingPurchases;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homepage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("Student")) {
            loggedInStudent = (Student) bundle.getSerializable("Student");
        }

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        String loggedInStudentId = loggedInStudent.getDataBaseID();
        DatabaseReference loggedInStudentRef = usersRef.child(loggedInStudentId);
        loggedInStudentRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loggedInStudent = snapshot.getValue(Student.class);
                listOfLessons = loggedInStudent.getPurchasedLessons();
                listOfPendingPurchases = loggedInStudent.getPendingPurchases();
                adapterForRecyclerViewYourLessons.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnPurchaseLesson = findViewById(R.id.btnPurchaseLesson);
        btnViewPurchaseStatus = findViewById(R.id.btnViewPurchaseStatus);
        recyclerViewYourLessons = findViewById(R.id.recyclerViewYourLessons);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewYourLessons.setLayoutManager(layoutManager);

        listOfLessons = loggedInStudent.getPurchasedLessons();
        //listOfLessons.add(new Lesson(new Topic("JImbo", "jimbo", 1, "jimbo"),16782782732108L, new Tutor("jim", "jim", "jim", "jim","jim","jim","jim"),"18932382-"));
        adapterForRecyclerViewYourLessons = new LessonList(listOfLessons);
        recyclerViewYourLessons.setAdapter(adapterForRecyclerViewYourLessons);

        listOfPendingPurchases = loggedInStudent.getPendingPurchases();


        btnPurchaseLesson.setOnClickListener(v -> {
            Bundle outwardBundle = new Bundle();
            outwardBundle.putSerializable("Student", loggedInStudent);
            Intent intent = new Intent(StudentHomepageActivity.this, SearchLessonsActivity.class);
            intent.putExtras(outwardBundle);
            startActivity(intent);
        });

        btnViewPurchaseStatus.setOnClickListener(v ->{
            PendingPurchasesDialogFragment dialogFragment = new PendingPurchasesDialogFragment(listOfPendingPurchases);
            dialogFragment.show(getSupportFragmentManager(), "PendingPurchasesDialogFragment");
        });

    }

    public void getLessonInformation(Lesson lesson) {
        Topic topic = lesson.getTopicToBeTaught();
        Tutor tutor = lesson.getTutorTeaching();
        Intent intent = new Intent(StudentHomepageActivity.this, LessonInformationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Lesson", lesson);
        bundle.putSerializable("Topic", topic);
        bundle.putSerializable("Tutor", tutor);
        bundle.putSerializable("Student", loggedInStudent);

        intent.putExtras(bundle);
        startActivity(intent);


    }

    public Student getLoggedInStudent() {
        return loggedInStudent;
    }

    public void addComplaintToDatabase(Complaint complaint) {
        DatabaseReference newNode = FirebaseDatabase.getInstance().getReference().child("complaints").push();
        String nodeKey = newNode.getKey();
        complaint.setDatabaseID(nodeKey);
        newNode.setValue(complaint);
        Toast.makeText(this, "Complaint Registration Successful", Toast.LENGTH_SHORT).show();

    }

    public void rate(Lesson lesson) {
        if (lesson.getTopicToBeTaught().getReviews() != null) {
            String loggedInStudentId = loggedInStudent.getDataBaseID();
            List<Review> reviews = lesson.getTopicToBeTaught().getReviews();

            for (Review review : reviews) {
                if (review.getReviewBy() != null && review.getReviewBy().getDataBaseID().equals(loggedInStudentId)) {
                    return;
                }
            }
        }
        RatingDialogFragment dialogFragment = new RatingDialogFragment();
        dialogFragment.setOnRatingCompleteListener((rating, title, description) -> {

            Review review = new Review();
            review.setRating((int) rating);
            review.setTitle(title);
            review.setDescription(description);
            review.setReviewBy(loggedInStudent);

            loggedInStudent.getPurchasedLessons().remove(lesson);

            lesson.getTopicToBeTaught().getReviews().add(review);
            lesson.getTopicToBeTaught().addRating((int) rating);
            lesson.getTutorTeaching().addRating((int) rating);

            loggedInStudent.getPurchasedLessons().add(lesson);

            DatabaseReference tutorRef = FirebaseDatabase.getInstance().getReference().child("users").child(lesson.getTutorTeaching().getDataBaseID());
            DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference().child("users").child(loggedInStudent.getDataBaseID());
            DatabaseReference topicRef = FirebaseDatabase.getInstance().getReference().child("topics").child(lesson.getTopicToBeTaught().getDatabaseID());

            tutorRef.setValue(lesson.getTutorTeaching());
            studentRef.setValue(loggedInStudent);
            topicRef.setValue(lesson.getTopicToBeTaught());


        });
        dialogFragment.show(getSupportFragmentManager(), "RatingDialogFragment");
    }
}