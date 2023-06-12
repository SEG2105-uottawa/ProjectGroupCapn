/**
 * WelcomeActivity display welcome message after logged in, also displays full name and role.
 * Handles logout function.
 */

package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // UI Components
        TextView detailsText = findViewById(R.id.detailsText);
        TextView roleMentionText = findViewById(R.id.roleMentionText);

        logout = findViewById(R.id.logout);

        Bundle bundle = getIntent().getExtras();

        // Login text for Student
        if (bundle != null && bundle.containsKey("Student")) {
            Student student = (Student) bundle.getSerializable("Student");
            String toDisplay = student.getFirstName() + " " + student.getLastName();
            detailsText.setText(toDisplay);
            String roleMention = "Logged in as a Student";
            roleMentionText.setText(roleMention);
        // Login text for Tutor
        }
        else if (bundle != null && bundle.containsKey("Tutor")) {
            Tutor tutor = (Tutor) bundle.getSerializable("Tutor");
            String toDisplay = tutor.getFirstName() + " " + tutor.getLastName();
            detailsText.setText(toDisplay);
            String roleMention = "Logged in as a Tutor";
            roleMentionText.setText(roleMention);
        }
        else if (bundle != null && bundle.containsKey("Administrator")) {
            Administrator administrator = (Administrator) bundle.getSerializable("Administrator");
            String toDisplay = administrator.getFirstName() + " " + administrator.getLastName();
            detailsText.setText(toDisplay);
            String roleMention = "Logged in as an Administrator";
            roleMentionText.setText(roleMention);
        }


        // Set Click listener to `logout` (direct to MainActivity.class if clicked)
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(WelcomeActivity.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
            }
        });

    }


}