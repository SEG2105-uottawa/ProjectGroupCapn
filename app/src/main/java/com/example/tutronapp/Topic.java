package com.example.tutronapp;

public class Topic {

    private String title;
    private String tutorDatabaseID;
    private int yearsOfExperience;
    private String description;



    public Topic(){

    }

    public Topic(String title, String tutorDatabaseID, int yearsOfExperience, String description) {
        this.title = title;
        this.tutorDatabaseID = tutorDatabaseID;
        this.yearsOfExperience = yearsOfExperience;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTutorDatabaseID() {
        return tutorDatabaseID;
    }

    public void setTutorDatabaseID(String tutorDatabaseID) {
        this.tutorDatabaseID = tutorDatabaseID;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
