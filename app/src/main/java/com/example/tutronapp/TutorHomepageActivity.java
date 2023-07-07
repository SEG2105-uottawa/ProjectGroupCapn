package com.example.tutronapp;

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

public class TutorHomepageActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOfferedTopics;
    private RecyclerView recyclerViewTopics;
    private OfferedTopicList adapterForOfferedTopicsRecycler;
    private TopicList adapterForTopicsRecycler;
    private FloatingActionButton btnAddTopic;
    private Tutor loggedInTutor;
    private DatabaseReference users;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_homepage);

        users = FirebaseDatabase.getInstance().getReference("users");

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("Tutor")){
            loggedInTutor = (Tutor) bundle.getSerializable("Tutor");
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
            AlertDialog.Builder builder = new AlertDialog.Builder(TutorHomepageActivity.this);
            View dialogView = LayoutInflater.from(TutorHomepageActivity.this).inflate(R.layout.dialog_add_topic, null);
            builder.setView(dialogView)
                    .setPositiveButton("Add", (dialog, which) -> {
                        EditText editTextTitle = dialogView.findViewById(R.id.editTextTitle);
                        EditText editTextDescription = dialogView.findViewById(R.id.editTextDescription);
                        EditText editTextExperience = dialogView.findViewById(R.id.editTextExperience);

                        String title = editTextTitle.getText().toString();
                        String description = editTextDescription.getText().toString();
                        int experience = Integer.parseInt(editTextExperience.getText().toString());

                        Topic topicToAdd = new Topic(title, loggedInTutor.getDataBaseID(), experience,
                                description);
                        yourTopics.add(topicToAdd);
                        loggedInTutor.setTopics(yourTopics);
                        adapterForTopicsRecycler.notifyDataSetChanged();
                        updateDatabase(loggedInTutor);
                        dialog.dismiss();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });






    }

    @SuppressLint("NotifyDataSetChanged")
    public void removeTopic(Topic topic) {
        loggedInTutor.getTopics().remove(topic);
        loggedInTutor.getOfferedTopics().remove(topic);
        updateDatabase(loggedInTutor);

        adapterForOfferedTopicsRecycler.notifyDataSetChanged();
        adapterForTopicsRecycler.notifyDataSetChanged();
    }

    private void updateDatabase(Tutor loggedInTutor) {
        users.child(loggedInTutor.getDataBaseID()).setValue(loggedInTutor);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void offerTopic(Topic topic) {
        List<Topic> currentlyOffered = loggedInTutor.getOfferedTopics();
        if (!currentlyOffered.contains(topic)){
            loggedInTutor.getOfferedTopics().add(topic);
            adapterForOfferedTopicsRecycler.notifyDataSetChanged();
            updateDatabase(loggedInTutor);
        }
        else {
            Toast.makeText(getApplicationContext(), "Invalid Attempt",
                Toast.LENGTH_SHORT).show();
        }
    }

    public void stopOffering(Topic topic) {
        loggedInTutor.getOfferedTopics().remove(topic);
        adapterForOfferedTopicsRecycler.notifyDataSetChanged();
        updateDatabase(loggedInTutor);
    }
}