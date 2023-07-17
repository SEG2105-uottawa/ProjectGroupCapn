package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
            DatabaseReference loggedInTutorRef = usersRef.child(loggedInTutor.getDataBaseID());
            loggedInTutorRef.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    loggedInTutor = (Tutor) snapshot.getValue(Tutor.class);
                    Intent intent = new Intent(TutorHomepageActivity.this, TutorEngagementsActivity.class);
                    Bundle outwardBundle = new Bundle();
                    outwardBundle.putSerializable("Tutor", loggedInTutor);
                    intent.putExtras(outwardBundle);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e ("FirebaseError", "DatabaseError: " + error.getMessage());
                }
            });

        });


    }
}