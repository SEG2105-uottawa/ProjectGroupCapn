package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentHomepageActivity extends AppCompatActivity {

    private Student loggedInStudent;
    private Button btnViewPurchaseStatus, btnPurchaseLesson;
    private RecyclerView recyclerViewYourLessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homepage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("Student")) {
            loggedInStudent = (Student) bundle.getSerializable("Student");
        }

        btnPurchaseLesson = findViewById(R.id.btnPurchaseLesson);
        btnViewPurchaseStatus = findViewById(R.id.btnViewPurchaseStatus);
        recyclerViewYourLessons = findViewById(R.id.recyclerViewYourLessons);




        btnPurchaseLesson.setOnClickListener(v -> {
            Intent intent = new Intent(StudentHomepageActivity.this, SearchLessonsActivity.class);
            startActivity(intent);
        });

    }
}