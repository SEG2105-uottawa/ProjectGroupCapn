package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchLessonsActivity extends AppCompatActivity {

    private DatabaseReference allOfferedTopics;
    private List<Topic> listOfTopics;
    private RecyclerView recyclerViewSearchResults;
    private SearchResultList adapterForRecyclerViewSearchResults;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_lessons);

        btnSearch = findViewById(R.id.btnSearch);


        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSearchResults.setLayoutManager(linearLayoutManager);

        allOfferedTopics = FirebaseDatabase.getInstance().getReference().child("topics");

        listOfTopics = new ArrayList<>();

        allOfferedTopics.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listOfTopics = new ArrayList<>();
                for (DataSnapshot topicSnapshot : dataSnapshot.getChildren()) {
                    Topic topic = topicSnapshot.getValue(Topic.class);
                    listOfTopics.add(topic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database Failure", Toast.LENGTH_SHORT).show();
            }
        });

        btnSearch.setOnClickListener(v -> {
            EditText editTextTutorName = findViewById(R.id.editTextTutorName);
            EditText editTextLanguageSpoken = findViewById(R.id.editTextLanguageSpoken);
            EditText editTextTopic = findViewById(R.id.editTextTopic);

            String tutorName = editTextTutorName.getText().toString();
            String languageSpoken = editTextLanguageSpoken.getText().toString();
            String topic = editTextTopic.getText().toString();

            List<Topic> sortedList = search(listOfTopics, tutorName, languageSpoken, topic);

            adapterForRecyclerViewSearchResults = new SearchResultList(sortedList);
            recyclerViewSearchResults.setAdapter(adapterForRecyclerViewSearchResults);
        });








    }


    private List<Topic> search(List<Topic> listOfTopics, String name, String languages, String topic){

        List<Topic> sortedList = listOfTopics;

        if (listOfTopics.isEmpty()){
            return sortedList;
        }
        if (!topic.trim().equals("")){
            sortedList = searchByTopic(listOfTopics, topic);
        }
        if (!name.trim().equals("")){
            sortedList = searchByName(sortedList,name);
        }
        if (!languages.trim().equals("")){
            sortedList = searchByLanguages(sortedList, languages);
        }

        return sortedList;
    }

    private List<Topic> searchByLanguages(List<Topic> sortedList, String languages) {
        List<Topic> resultList = new ArrayList<>();

        for (Topic topic : sortedList) {
            if (topic.getOfferedInLanguages().contains(languages)) {
                resultList.add(topic);
            }
        }

        return resultList;
    }

    private List<Topic> searchByName(List<Topic> sortedList, String name) {
        List<Topic> resultList = new ArrayList<>();

        for (Topic topic : sortedList) {
            if (topic.getTutorLastName().equalsIgnoreCase(name)) {
                resultList.add(topic);
            }
        }

        return resultList;
    }

    private List<Topic> searchByTopic(List<Topic> listOfTopics, String topic) {
        List<Topic> resultList = new ArrayList<>();

        for (Topic t : listOfTopics) {
            if (t.getTitle().equalsIgnoreCase(topic)) {
                resultList.add(t);
            }
        }

        return resultList;
    }



}