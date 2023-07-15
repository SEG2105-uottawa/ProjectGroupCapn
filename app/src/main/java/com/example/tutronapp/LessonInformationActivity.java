package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LessonInformationActivity extends AppCompatActivity {

    private Lesson lessonToDisplay;
    private Topic topicToDisplay;
    private Tutor tutorTeaching;
    private Student loggedInStudent;
    private TextView textViewTopicTitle, textViewTopicDescription, textViewYearsOfExperience;
    private TextView textViewTopicRating, textViewHourlyRate, textViewLessonDate;
    private TextView textViewTutorName, textViewTutorDescription, textViewLanguages, textViewEducation;
    private Button btnUserReviews;
    private DatabaseReference purchases;
    private DatabaseReference users;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_information);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            lessonToDisplay = (Lesson) bundle.getSerializable("Lesson");
            topicToDisplay = (Topic) bundle.getSerializable("Topic");
            tutorTeaching = (Tutor) bundle.getSerializable("Tutor");
            loggedInStudent = (Student) bundle.getSerializable("Student");
        }


        textViewTopicTitle = findViewById(R.id.textViewTopicTitle);
        textViewTopicDescription = findViewById(R.id.textViewTopicDescription);
        textViewYearsOfExperience = findViewById(R.id.textViewYearsOfExperience);
        textViewTopicRating = findViewById(R.id.textViewTopicRating);
        textViewHourlyRate = findViewById(R.id.textViewHourlyRate);
        textViewLessonDate = findViewById(R.id.textViewLessonDate);

        textViewTutorName = findViewById(R.id.textViewTutorName);
        textViewTutorDescription = findViewById(R.id.textViewTutorDescripction);
        textViewLanguages = findViewById(R.id.textViewLanguages);
        textViewEducation = findViewById(R.id.textViewEducation);


        textViewTopicTitle.setText(topicToDisplay.getTitle());
        textViewTopicDescription.setText(topicToDisplay.getDescription());
        textViewYearsOfExperience.setText(String.valueOf(topicToDisplay.getYearsOfExperience()) + " Years");
        textViewHourlyRate.setText(String.valueOf(topicToDisplay.getHourlyRate()));
        if (topicToDisplay.getRating() < 0) {
            textViewTopicRating.setText("Insufficient Ratings");
        } else if (topicToDisplay.getRating() <= 5) {
            textViewTopicRating.setText(String.valueOf(topicToDisplay.getRating()));
        } else {
            textViewTopicRating.setText("Error");
        }
        long dateInMillis = lessonToDisplay.getDateOfLesson();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(new Date(dateInMillis));
        textViewLessonDate.setText(formattedDate);
        textViewTutorName.setText(tutorTeaching.getName());
        textViewTutorDescription.setText(tutorTeaching.getShortDescription());
        textViewEducation.setText(tutorTeaching.getEducationLevel());
        textViewLanguages.setText(tutorTeaching.getNativeLanguages());


        btnUserReviews = findViewById(R.id.btnUserReviews);
        btnUserReviews.setOnClickListener(v -> {
            ReviewsDialogFragment dialogFragment = new ReviewsDialogFragment(topicToDisplay.getReviews());
            dialogFragment.show(getSupportFragmentManager(), "ReviewsDialogFragment");
        });
    }
}