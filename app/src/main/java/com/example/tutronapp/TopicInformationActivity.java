package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class TopicInformationActivity extends AppCompatActivity {
    private Topic topicToDisplay;
    private Tutor tutorTeaching;
    private Student loggedInStudent;
    private TextView textViewTopicTitle, textViewTopicDescription, textViewYearsOfExperience;
    private TextView textViewTopicRating, textViewHourlyRate;
    private TextView textViewTutorName, textViewTutorDescription, textViewLanguages, textViewEducation;
    private Button btnUserReviews, btnTutorSchedule;
    private long selectedDate;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_information);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null ){
            topicToDisplay = (Topic) bundle.getSerializable("Topic");
            tutorTeaching = (Tutor) bundle.getSerializable("Tutor");
            loggedInStudent = (Student) bundle.getSerializable("Student");
        }

        textViewTopicTitle = findViewById(R.id.textViewTopicTitle);
        textViewTopicDescription = findViewById(R.id.textViewTopicDescription);
        textViewYearsOfExperience = findViewById(R.id.textViewYearsOfExperience);
        textViewTopicRating = findViewById(R.id.textViewTopicRating);
        textViewHourlyRate = findViewById(R.id.textViewHourlyRate);

        textViewTutorName = findViewById(R.id.textViewTutorName);
        textViewTutorDescription = findViewById(R.id.textViewTutorDescripction);
        textViewLanguages = findViewById(R.id.textViewLanguages);
        textViewEducation = findViewById(R.id.textViewEducation);


        textViewTopicTitle.setText(topicToDisplay.getTitle());
        textViewTopicDescription.setText(topicToDisplay.getDescription());
        textViewYearsOfExperience.setText(String.valueOf(topicToDisplay.getYearsOfExperience()) + " Years");
        textViewHourlyRate.setText(String.valueOf(topicToDisplay.getHourlyRate()));
        if (topicToDisplay.getRating() < 0){
            textViewTopicRating.setText("Insufficient Ratings");
        }
        else if (topicToDisplay.getRating() <= 5){
            textViewTopicRating.setText(String.valueOf(topicToDisplay.getRating()));
        }
        else{
            textViewTopicRating.setText("Error");
        }
        textViewTutorName.setText(tutorTeaching.getName());
        textViewTutorDescription.setText(tutorTeaching.getShortDescription());
        textViewEducation.setText(tutorTeaching.getEducationLevel());
        textViewLanguages.setText(tutorTeaching.getNativeLanguages());

        btnTutorSchedule = findViewById(R.id.btnTutorSchedule);
        btnTutorSchedule.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_tutor_available_schedule, null);
            builder.setView(dialogView);
            CalendarView calendarView = dialogView.findViewById(R.id.calendarView);
            builder.setPositiveButton("Select", (dialog, which) -> {
                selectedDate = calendarView.getDate();
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        btnUserReviews = findViewById(R.id.btnUserReviews);
        btnUserReviews.setOnClickListener(v -> {
            ReviewsDialogFragment dialogFragment = new ReviewsDialogFragment(topicToDisplay.getReviews());
            dialogFragment.show(getSupportFragmentManager(), "ReviewsDialogFragment");
        });

    }
}