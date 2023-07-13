package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TopicInformationActivity extends AppCompatActivity {
    private Topic topicToDisplay;
    private TextView textViewTopicTitle, textViewTopicDescription, textViewYearsOfExperience;
    private TextView textViewTopicRating, textViewHourlyRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_information);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("Topic")){
            topicToDisplay = (Topic) bundle.getSerializable("Topic");
        }

        textViewTopicTitle = findViewById(R.id.textViewTopicTitle);
        textViewTopicDescription = findViewById(R.id.textViewTopicDescription);
        textViewYearsOfExperience = findViewById(R.id.textViewYearsOfExperience);
        textViewTopicRating = findViewById(R.id.textViewTopicRating);
        textViewHourlyRate = findViewById(R.id.textViewHourlyRate);

        textViewTopicTitle.setText(topicToDisplay.getTitle());
        textViewTopicDescription.setText(topicToDisplay.getDescription());
        textViewYearsOfExperience.setText(String.valueOf(topicToDisplay.getYearsOfExperience()));
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
    }
}