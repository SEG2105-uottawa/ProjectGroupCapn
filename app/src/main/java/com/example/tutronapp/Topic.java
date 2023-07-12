package com.example.tutronapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Topic implements Serializable {

    private String title;
    private String databaseID;
    private String tutorDatabaseID;
    private int yearsOfExperience;
    private String description;
    private String offeredInLanguages = "";
    private double hourlyRate;
    private String tutorLastName = "";






    public Topic(){

    }

    public Topic(String title, String tutorDatabaseID, int yearsOfExperience, String description) {
        this.title = title;
        this.tutorDatabaseID = tutorDatabaseID;
        this.yearsOfExperience = yearsOfExperience;
        this.description = description;
    }


    public Topic(String title, String tutorDatabaseID, int yearsOfExperience, String description, String offeredInLanguages, double hourlyRate, String tutorLastName) {
        this.title = title;
        this.tutorDatabaseID = tutorDatabaseID;
        this.yearsOfExperience = yearsOfExperience;
        this.description = description;
        this.offeredInLanguages = offeredInLanguages;
        this.hourlyRate = hourlyRate;
        this.tutorLastName = tutorLastName;
    }


    @Override
    public boolean equals(Object object){
        if (object == null || object.getClass() != this.getClass()){
            return false;
        }
        if (((Topic) object).getTitle().equals(this.title)){
            return true;
        }
        return false;
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

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String databaseID) {
        this.databaseID = databaseID;
    }


    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getOfferedInLanguages() {
        return offeredInLanguages;
    }

    public void setOfferedInLanguages(String offeredInLanguages) {
        this.offeredInLanguages = offeredInLanguages;
    }

    public String getTutorLastName() {
        return tutorLastName;
    }

    public void setTutorLastName(String tutorLastName) {
        this.tutorLastName = tutorLastName;
    }
}
