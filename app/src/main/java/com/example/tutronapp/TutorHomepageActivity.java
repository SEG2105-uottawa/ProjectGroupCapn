package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TutorHomepageActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOfferedTopics;
    private RecyclerView recyclerViewTopics;
    private TopicList adapterForOfferedTopicsRecycler;
    private TopicList adapterForTopicsRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_homepage);

        LinearLayoutManager layoutManagerForOfferedTopics = new LinearLayoutManager(this);
        LinearLayoutManager layoutManagerForTopics = new LinearLayoutManager(this);
        recyclerViewTopics = findViewById(R.id.recyclerViewTopics);
        recyclerViewOfferedTopics = findViewById(R.id.recyclerViewOfferedTopics);
        recyclerViewTopics.setLayoutManager(layoutManagerForTopics);
        recyclerViewOfferedTopics.setLayoutManager(layoutManagerForOfferedTopics);

        List<Topic> offeredTopics = new ArrayList<>();
        offeredTopics.add(new Topic("Alchemy", "7", 1700,
                "Learn to convert metals to gold "));

        adapterForOfferedTopicsRecycler = new TopicList(offeredTopics);
        adapterForTopicsRecycler = new TopicList(offeredTopics);

        recyclerViewOfferedTopics.setAdapter(adapterForOfferedTopicsRecycler);
        recyclerViewTopics.setAdapter(adapterForTopicsRecycler);



    }
}