package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StudentHomepageActivity extends AppCompatActivity {

    private Student loggedInStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homepage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("Student")) {
            loggedInStudent = (Student) bundle.getSerializable("Student");
        }

    }
}