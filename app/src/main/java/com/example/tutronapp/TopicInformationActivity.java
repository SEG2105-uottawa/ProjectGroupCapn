package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TopicInformationActivity extends AppCompatActivity {
    private Topic topicToDisplay;
    private Tutor tutorTeaching;
    private Student loggedInStudent;
    private TextView textViewTopicTitle, textViewTopicDescription, textViewYearsOfExperience;
    private TextView textViewTopicRating, textViewHourlyRate;
    private TextView textViewTutorName, textViewTutorDescription, textViewLanguages, textViewEducation;
    private Button btnUserReviews, btnTutorSchedule, btnPurchase;
    private long selectedDate;
    private DatabaseReference purchases;
    private DatabaseReference users;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_information);

        users = FirebaseDatabase.getInstance().getReference().child("users");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
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
        if (topicToDisplay.getRating() < 0) {
            textViewTopicRating.setText("Insufficient Ratings");
        } else if (topicToDisplay.getRating() <= 5) {
            textViewTopicRating.setText(String.valueOf(topicToDisplay.getRating()));
        } else {
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
            calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, dayOfMonth, 0, 0, 0);
                selectedCalendar.set(Calendar.MILLISECOND, 0);
                selectedDate = selectedCalendar.getTimeInMillis();
            });
            builder.setPositiveButton("Select", (dialog, which) -> {
                dialog.dismiss();
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

        btnPurchase = findViewById(R.id.btnPurchase);
        btnPurchase.setOnClickListener(v -> {
            Purchase purchase = new Purchase(loggedInStudent.getDataBaseID(), tutorTeaching.getDataBaseID(), topicToDisplay.getTutorDatabaseID(), selectedDate, System.currentTimeMillis(), false);

            if (!checkSpamPurchase(purchase) && selectedDate > System.currentTimeMillis()) {
                purchases = FirebaseDatabase.getInstance().getReference().child("purchases");
                addPurchase(purchase);
                tutorTeaching.getTopicPurchases().add(purchase);
                loggedInStudent.getPendingPurchases().add(purchase);
                updateStudent(loggedInStudent);
                updateTutor(tutorTeaching);

                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
                String formattedDate = dateFormat.format(new Date(purchase.getDateForLesson()));

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Purchase Request Success");
                builder.setMessage("A purchase request for " + topicToDisplay.getTitle() +
                        " has been sent to " + tutorTeaching.getName() +
                        " for " + formattedDate + ".\n\n" +
                        tutorTeaching.getName() + " will contact you with the details once approved.\n\n" +
                        "Thank you for your purchase " + loggedInStudent.getName());

                builder.setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    finish();
                });

                AlertDialog dialog = builder.create();
                dialog.setOnShowListener(dialogInterface -> {
                    Button buttonPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    buttonPositive.setOnClickListener(view -> {
                        dialog.dismiss();
                        finish();
                    });
                });
                dialog.show();

            }
            else if (checkSpamPurchase(purchase)){
                Toast.makeText(TopicInformationActivity.this, "A similar purchase already exists", Toast.LENGTH_SHORT).show();
            }
            else if (System.currentTimeMillis() > selectedDate){
                Toast.makeText(TopicInformationActivity.this, "Please select a valid date", Toast.LENGTH_SHORT).show();
            }

        });



    }


    private boolean checkSpamPurchase(Purchase purchase) {
        for (Purchase existingPurchase : loggedInStudent.getPendingPurchases()) {
            if (existingPurchase.getTutorTeachingDatabaseID().equals(purchase.getTutorTeachingDatabaseID()) &&
                    existingPurchase.getLessonTaughtDatabaseID().equals(purchase.getLessonTaughtDatabaseID()) &&
                    existingPurchase.getDateForLesson().equals(purchase.getDateForLesson())) {
                return true;
            }
        }
        return false;
    }


    private void updateTutor(Tutor tutor) {
        users.child(tutor.getDataBaseID()).setValue(tutor);
    }
    private void updateStudent(Student student){
        users.child(student.getDataBaseID()).setValue(student);
    }


    private void addPurchase(Purchase purchase) {
        DatabaseReference newNode = purchases.push();
        String nodeKey = newNode.getKey();
        purchase.setDatabaseID(nodeKey);
        newNode.setValue(purchase);
    }


}