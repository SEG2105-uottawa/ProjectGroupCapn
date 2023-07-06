/**
 * MainActivity is the entry of the application, it initialize firebase and provides two functions,
 * login(button) and signup(button), which navigate to LoginActivity and SignupActivity respectively.
 */
package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this); // Initialize Firebase

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnSignup = findViewById(R.id.btnSignup);
        
        // set Click listener to `btnLogin` (direct to LoginActivity.class if clicked)
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            //tutorHomepageTest();
        });

        // set Click listener to `btnSignup` (direct to SignupActivity.class if clicked)
        btnSignup.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        });

        // Add complaints to the database
        //addComplaintsToDatabase(); // uncomment if u want to add a test complain
    }

    private void tutorHomepageTest() {
        Topic alchemy = new Topic("Alchemy", "71", 5, "Learn to convert metals to gold");
        List<Topic> offeredCourses = new ArrayList<>();
        List<Topic> allCourses = new ArrayList<>();
        allCourses.add(alchemy);
        offeredCourses.add(alchemy);

        Tutor tutor = new Tutor("James", "Jones", "jimmyjohn@email.com", "Jimmy", "Universty", "Native Lang", "Hi");
        tutor.setTopics(allCourses);
        tutor.setOfferedTopics(offeredCourses);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Tutor",tutor);
        Intent intent = new Intent(MainActivity.this, TutorHomepageActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void addComplaintsToDatabase() {
        // Test complain
        DatabaseReference complaintsRef = FirebaseDatabase.getInstance().getReference("complaints");
        String complaintTitle1 = "Test1(Delete)";
        String complaintContent1 = "Test delete";
        User tutor1 = new Tutor("testDelete", "tutor",
                "testDelete@tutor.com", "abc");
        User student1 = new Student("testDelete", "student",
                "testDelete@student.com", "abc", new CreditCard(), new Address());
        Complaint complaint1 = new Complaint((Tutor) tutor1, (Student) student1, complaintTitle1,
                complaintContent1, "open");
        String complaintKey1 = complaintsRef.push().getKey();
        complaintsRef.child(complaintKey1).setValue(complaint1);
    }

}