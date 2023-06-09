package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView detailsText = findViewById(R.id.detailsText);
        TextView roleMentionText = findViewById(R.id.roleMentionText);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("Student")) {
            Student student = (Student) bundle.getSerializable("Student");
            String toDisplay = student.getFirstName() + " " + student.getLastName();
            detailsText.setText(toDisplay);
            String roleMention = "Logged in as a Student";
            roleMentionText.setText(roleMention);

        }
        else if (bundle != null && bundle.containsKey("Tutor")) {
            Tutor tutor = (Tutor) bundle.getSerializable("Tutor");
            String toDisplay = tutor.getFirstName() + " " + tutor.getLastName();
            detailsText.setText(toDisplay);
            String roleMention = "Logged in as a Tutor";
            roleMentionText.setText(roleMention);
        }


    }
}