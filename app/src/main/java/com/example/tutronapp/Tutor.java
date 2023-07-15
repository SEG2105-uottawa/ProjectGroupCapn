/**
 * Tutor is a subclass of User class that represents a tutor in the application.
 * The class provides constructors to initialize the tutor object, including name, email, and
 * password. The class also includes a constant field "role" that specifies as "Tutor"
 */

package com.example.tutronapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends User implements Serializable {

    private final String role = "Tutor";
    private String shortDescription;
    private String nativeLanguages;
    private String educationLevel;
    private Long suspensionEndDate;
    private List<Topic> offeredTopics = new ArrayList<>();
    private List<Topic> topics = new ArrayList<>();
    private List<Purchase> topicPurchases = new ArrayList<>();
    private List<Lesson> lessonPurchases = new ArrayList<>();
    private double rating = -1;
    private int cumulativeRating = 0;
    private int numberOfRatings = 0;

    public Tutor() {

    }

    public Tutor(String firstName, String lastName, String emailAddress, String password) {
        super(firstName, lastName, emailAddress, password, "Tutor");
    }

    public Tutor(String firstName, String lastName, String emailAddress, String password, String educationLevel, String native_languages, String shortDescription) {
        super(firstName, lastName, emailAddress, password, "Tutor");
        this.educationLevel = educationLevel;

        this.nativeLanguages = native_languages;

        this.shortDescription = shortDescription;
    }

    public String getRole() {
        return role;
    }


    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getNativeLanguages() {
        return nativeLanguages;
    }

    public void setNativeLanguages(String nativeLanguages) {
        this.nativeLanguages = nativeLanguages;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }


    public Long getSuspensionEndDate() {
        return suspensionEndDate;
    }

    public void setSuspensionEndDate(Long suspensionEndDate) {
        this.suspensionEndDate = suspensionEndDate;
    }

    public List<Topic> getOfferedTopics() {
        return offeredTopics;
    }

    public void setOfferedTopics(List<Topic> offeredTopics) {
        this.offeredTopics = offeredTopics;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Purchase> getTopicPurchases() {
        return topicPurchases;
    }

    public void setTopicPurchases(List<Purchase> topicPurchases) {
        this.topicPurchases = topicPurchases;
    }

    public int getCumulativeRating() {
        return cumulativeRating;
    }

    public void setCumulativeRating(int cumulativeRating) {
        this.cumulativeRating = cumulativeRating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void addRating(int rating){
        if (rating < 6 && rating > 0) {
            numberOfRatings++;
            cumulativeRating = cumulativeRating + rating;
            double newRating = (((double) cumulativeRating) / ((double) numberOfRatings));
            if (newRating < 6 && newRating > 0) {
                setRating(newRating);
            }
        }
    }

    public void changeCurrentRating(int currentRating, int newRating){
        numberOfRatings --;
        cumulativeRating = cumulativeRating - currentRating;
        addRating(newRating);
    }

    public List<Lesson> getLessonPurchases() {
        return lessonPurchases;
    }

    public void setLessonPurchases(List<Lesson> lessonPurchases) {
        this.lessonPurchases = lessonPurchases;
    }
}


