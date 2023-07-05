package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TutorHomepageActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOfferedTopics;
    private RecyclerView recyclerViewTopics;
    private TopicList adapterForOfferedTopicsRecycler;
    private TopicList adapterForTopicsRecycler;
    private FloatingActionButton btnAddTopic;
    private Tutor loggedInTutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_homepage);

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

        adapterForOfferedTopicsRecycler = new TopicList(offeredTopics);
        adapterForTopicsRecycler = new TopicList(yourTopics);

        recyclerViewOfferedTopics.setAdapter(adapterForOfferedTopicsRecycler);
        recyclerViewTopics.setAdapter(adapterForTopicsRecycler);





    }
}