package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        List<Lesson> purchasedLessons = loggedInStudent.getPurchasedLessons();
        //purchasedLessons.add(new Lesson(new Topic("JImbo", "jimbo", 1, "jimbo"),16782782732108L, new Tutor("jim", "jim", "jim", "jim","jim","jim","jim"),"18932382-"));
        adapterForRecyclerViewYourLessons = new LessonList(purchasedLessons);
        recyclerViewYourLessons.setAdapter(adapterForRecyclerViewYourLessons);






        btnPurchaseLesson.setOnClickListener(v -> {
            Bundle outwardBundle = new Bundle();
            outwardBundle.putSerializable("Student", loggedInStudent);
            Intent intent = new Intent(StudentHomepageActivity.this, SearchLessonsActivity.class);
            intent.putExtras(outwardBundle);
            startActivity(intent);
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
}