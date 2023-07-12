package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

            CheckBox checkBoxYearsOfExperience = findViewById(R.id.checkBoxYearsOfExperience);
            CheckBox checkBoxUserRatings = findViewById(R.id.checkBoxUserRatings);
            CheckBox checkBoxHourlyRate = findViewById(R.id.checkBoxHourlyRate);

            String tutorName = editTextTutorName.getText().toString().trim();
            String languageSpoken = editTextLanguageSpoken.getText().toString().trim();
            String topic = editTextTopic.getText().toString().trim();

            boolean verifyOnlyOneCheckBox = (checkBoxYearsOfExperience.isChecked() ? 1 : 0) +
                                            (checkBoxUserRatings.isChecked() ? 1 : 0) +
                                            (checkBoxHourlyRate.isChecked() ? 1 : 0) < 2;

            if (verifyOnlyOneCheckBox){

                List<Topic> searchList = search(listOfTopics, tutorName, languageSpoken, topic);
                List<Topic> sortedList = new ArrayList<>();
                if (checkBoxHourlyRate.isChecked()) {
                    sortedList = sortByHourlyRate(searchList);
                }
                if (checkBoxUserRatings.isChecked()){
                    sortedList = sortByUserRatings(searchList);
                }
                if (checkBoxYearsOfExperience.isChecked()){
                    sortedList = sortByYearsOfExperience(searchList);
                }
                if (sortedList.isEmpty()){
                    sortedList = searchList;
                }

                adapterForRecyclerViewSearchResults = new SearchResultList(sortedList);
                recyclerViewSearchResults.setAdapter(adapterForRecyclerViewSearchResults);

            }
            else{
                Toast.makeText(this, "Please select only one sorting option", Toast.LENGTH_SHORT).show();
            }


        });








    }

    private List<Topic> sortByYearsOfExperience(List<Topic> searchList) {
        Collections.sort(searchList, (topic1, topic2) -> topic2.getYearsOfExperience() - topic1.getYearsOfExperience());
        return searchList;
    }

    private List<Topic> sortByUserRatings(List<Topic> searchList) {
        Collections.sort(searchList, (t1, t2) -> Double.compare(t2.getRating(), t1.getRating()));
        return searchList;
    }

    private List<Topic> sortByHourlyRate(List<Topic> searchList) {
        Collections.sort(searchList, Comparator.comparingDouble(Topic::getHourlyRate));
        return searchList;
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