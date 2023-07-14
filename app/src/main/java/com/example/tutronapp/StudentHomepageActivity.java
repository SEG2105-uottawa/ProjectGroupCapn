package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        String loggedInStudentId = loggedInStudent.getDataBaseID();
        DatabaseReference loggedInStudentRef = usersRef.child(loggedInStudentId);
        loggedInStudentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loggedInStudent = snapshot.getValue(Student.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnPurchaseLesson = findViewById(R.id.btnPurchaseLesson);
        btnViewPurchaseStatus = findViewById(R.id.btnViewPurchaseStatus);
        recyclerViewYourLessons = findViewById(R.id.recyclerViewYourLessons);




        btnPurchaseLesson.setOnClickListener(v -> {
            Bundle outwardBundle = new Bundle();
            outwardBundle.putSerializable("Student", loggedInStudent);
            Intent intent = new Intent(StudentHomepageActivity.this, SearchLessonsActivity.class);
            intent.putExtras(outwardBundle);
            startActivity(intent);
        });

    }
}