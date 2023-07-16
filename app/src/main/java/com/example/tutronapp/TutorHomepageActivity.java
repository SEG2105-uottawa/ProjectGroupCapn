package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class TutorHomepageActivity extends AppCompatActivity {

    private Tutor loggedInTutor;
    private TextView textViewTutorName, textViewTutorDescription, textViewLanguages, textViewEducation, textViewRating;
    private Button btnYourTopics, btnLessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_homepage);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("Tutor")) {
            loggedInTutor = (Tutor) bundle.getSerializable("Tutor");
        }

        textViewTutorName = findViewById(R.id.textViewTutorName);
        textViewTutorDescription = findViewById(R.id.textViewTutorDescripction);
        textViewLanguages = findViewById(R.id.textViewLanguages);
        textViewEducation = findViewById(R.id.textViewEducation);
        textViewRating = findViewById(R.id.textViewRating);
        btnYourTopics = findViewById(R.id.btnYourTopics);
        btnLessons = findViewById(R.id.btnLessons);

        textViewTutorName.setText(loggedInTutor.getName());
        textViewTutorDescription.setText(loggedInTutor.getShortDescription());
        textViewEducation.setText(loggedInTutor.getEducationLevel());
        textViewLanguages.setText(loggedInTutor.getNativeLanguages());
        if (loggedInTutor.getRating() < 0) {
            textViewRating.setText("Insufficient Ratings");
        } else if (loggedInTutor.getRating() <= 5) {
            textViewRating.setText(String.valueOf(loggedInTutor.getRating()));
        } else {
            textViewRating.setText("Error");
        }

        btnYourTopics.setOnClickListener(v ->{
            Intent intent = new Intent(TutorHomepageActivity.this, TutorTopicsActivity.class);
            Bundle outwardBundle = new Bundle();
            outwardBundle.putSerializable("Tutor", loggedInTutor);
            intent.putExtras(outwardBundle);
            startActivity(intent);
        });

        btnLessons.setOnClickListener(v ->{
            Intent intent = new Intent(TutorHomepageActivity.this, TutorEngagementsActivity.class);
            Bundle outwardBundle = new Bundle();
            outwardBundle.putSerializable("Tutor", loggedInTutor);
            intent.putExtras(outwardBundle);
            startActivity(intent);
        });


    }
}