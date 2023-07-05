package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TutorHomepageActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOfferedTopics;
    private TopicList adapterForOfferedTopicsRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_homepage);

        recyclerViewOfferedTopics = findViewById(R.id.recyclerViewOfferedTopics);

        List<String> offeredTopics = new ArrayList<>();
        offeredTopics.add("Jim");
        adapterForOfferedTopicsRecycler = new TopicList(offeredTopics);

        recyclerViewOfferedTopics.setAdapter(adapterForOfferedTopicsRecycler);



    }
}