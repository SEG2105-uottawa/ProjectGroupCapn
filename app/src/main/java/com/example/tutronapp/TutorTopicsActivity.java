package com.example.tutronapp;
/**
 * An Activity for managing Topics Offered and added by a tutor
 * @author Abhay A
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class TutorTopicsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOfferedTopics;
    private RecyclerView recyclerViewTopics;
    private OfferedTopicList adapterForOfferedTopicsRecycler;
    private TopicList adapterForTopicsRecycler;
    private FloatingActionButton btnAddTopic;
    private Tutor loggedInTutor;
    private DatabaseReference users;
    private DatabaseReference currentlyOfferedTopics;

    private OfferedTopicList recyclerViewAdapter;

    private static List<Topic> offeredTopicList;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_topics);

        users = FirebaseDatabase.getInstance().getReference("users");
        currentlyOfferedTopics = FirebaseDatabase.getInstance().getReference("topics");

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("Tutor")){
            loggedInTutor = (Tutor) bundle.getSerializable("Tutor");
        } else{
            Toast.makeText(getApplicationContext(), "Error: Tutor data not found",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        btnAddTopic = findViewById(R.id.btnAddTopic);

        LinearLayoutManager layoutManagerForOfferedTopics = new LinearLayoutManager(this);
        LinearLayoutManager layoutManagerForTopics = new LinearLayoutManager(this);
        recyclerViewTopics = findViewById(R.id.recyclerViewTopics);
        recyclerViewOfferedTopics = findViewById(R.id.recyclerViewOfferedTopics);
        recyclerViewTopics.setLayoutManager(layoutManagerForTopics);
        recyclerViewOfferedTopics.setLayoutManager(layoutManagerForOfferedTopics);

        List<Topic> offeredTopics = loggedInTutor.getOfferedTopics();
        List<Topic> yourTopics = loggedInTutor.getTopics();


        adapterForOfferedTopicsRecycler = new OfferedTopicList(offeredTopics);
        adapterForTopicsRecycler = new TopicList(yourTopics);

        recyclerViewOfferedTopics.setAdapter(adapterForOfferedTopicsRecycler);
        recyclerViewTopics.setAdapter(adapterForTopicsRecycler);




        btnAddTopic.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(TutorTopicsActivity.this);
            View dialogView = LayoutInflater.from(TutorTopicsActivity.this).inflate(R.layout.dialog_add_topic, null);
            builder.setView(dialogView)
                    .setPositiveButton("Add", (dialog, which) -> {
                        EditText editTextTitle = dialogView.findViewById(R.id.editTextTitle);
                        EditText editTextDescription = dialogView.findViewById(R.id.editTextDescription);
                        EditText editTextExperience = dialogView.findViewById(R.id.editTextExperience);
                        EditText editTextHourlyRate = dialogView.findViewById(R.id.editTextHourlyRate);

                        String title = editTextTitle.getText().toString();
                        String description = editTextDescription.getText().toString();
                        String experienceText = editTextExperience.getText().toString();
                        String hourlyRate = editTextHourlyRate.getText().toString();

                        if (title.isEmpty() || description.isEmpty() || experienceText.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //handles experience input errors
                        int experience;

                        try {
                            experience = Integer.parseInt(experienceText);
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Invalid experience value", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        double hourlyRateDouble;
                        try{
                            hourlyRateDouble = Double.parseDouble(hourlyRate);
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Invalid hourly rate", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (experience < 0) {
                            Toast.makeText(getApplicationContext(), "Experience cannot be negative", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (hourlyRateDouble < 0) {
                            Toast.makeText(getApplicationContext(), "Hourly rate cannot be negative", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (adapterForTopicsRecycler.getItemCount() < 20) {
                            Topic topicToAdd = new Topic(title, loggedInTutor.getDataBaseID(), experience,
                                    description, loggedInTutor.getNativeLanguages(), hourlyRateDouble, loggedInTutor.getLastName());
                            yourTopics.add(topicToAdd);
                            loggedInTutor.setTopics(yourTopics);
                            adapterForTopicsRecycler.notifyDataSetChanged();
                            updateDatabaseForTutor(loggedInTutor);
                            dialog.dismiss();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "List Full, " +
                                            "Please retry after removing a topic",
                                    Toast.LENGTH_SHORT).show();
                        }

                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });






    }

    /**
     * Removes a topic from both Offered Topic and Your topic
     * @param topic Topic to be removed
     */
    @SuppressLint("NotifyDataSetChanged")
    public void removeTopic(Topic topic) {
        loggedInTutor.getTopics().remove(topic);
        loggedInTutor.getOfferedTopics().remove(topic);
        updateDatabaseForTutor(loggedInTutor);

        adapterForOfferedTopicsRecycler.notifyDataSetChanged();
        adapterForTopicsRecycler.notifyDataSetChanged();
    }

    /**
     * Takes a tutor as an input, assumed to be up to date with all information locally
     * and updates that information to the database
     * @param loggedInTutor Tutor up to date with local information
     */
    private void updateDatabaseForTutor(Tutor loggedInTutor) {
        users.child(loggedInTutor.getDataBaseID()).setValue(loggedInTutor);
    }


    /**
     * Takes a topic from Your Topics and adds them to Offered Topics if its not already present.
     * @param topic Topic to be offered
     */
    @SuppressLint("NotifyDataSetChanged")
    public void offerTopic(Topic topic) {
        List<Topic> currentlyOffered = loggedInTutor.getOfferedTopics();
        if (adapterForOfferedTopicsRecycler.getItemCount() < 5) {
            if (!currentlyOffered.contains(topic)) {
                loggedInTutor.getOfferedTopics().add(topic);
                addTopicToDatabase(topic);
                adapterForOfferedTopicsRecycler.notifyDataSetChanged();
                updateDatabaseForTutor(loggedInTutor);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Attempt",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "List full, please retry after removing a topic",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Removes a topic from Offered Topics
     * @param topic Topic to be removed from Offered
     */
    public void stopOffering(Topic topic) {
        loggedInTutor.getOfferedTopics().remove(topic);
        removeTopicFromDatabase(topic);
        adapterForOfferedTopicsRecycler.notifyDataSetChanged();
        updateDatabaseForTutor(loggedInTutor);
    }

    public void setOfferedTopicsAdapter(OfferedTopicList adapter) {
        recyclerViewOfferedTopics.setAdapter(adapter);
    }

    private void addTopicToDatabase(Topic topic){
        DatabaseReference newNode = currentlyOfferedTopics.push();
        String nodeKey = newNode.getKey();
        topic.setDatabaseID(nodeKey);
        newNode.setValue(topic);
    }

    private void removeTopicFromDatabase(Topic topic){
        currentlyOfferedTopics.child(topic.getDatabaseID()).removeValue();
    }


    public void openReviewsDialogFragment(Topic topic) {
        ReviewsDialogFragment dialogFragment = new ReviewsDialogFragment(topic.getReviews());
        dialogFragment.show(getSupportFragmentManager(), "ReviewsDialogFragment");
    }
}