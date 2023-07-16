package com.example.tutronapp;

import java.io.Serializable;

public class Review implements Serializable {

    private int rating;
    private String title;
    private String description;
    private String reviewBy;
    private String reviewByDatabaseID;

    public Review() {
    }

    public Review(int rating) {
        this.rating = rating;
    }

    public Review(int rating, String title, String description) {
        this.rating = rating;
        this.title = title;
        this.description = description;
    }

    public Review(int rating, String title, String description, String reviewBy, String reviewByDatabaseID) {
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.reviewBy = reviewBy;
        this.reviewByDatabaseID = reviewByDatabaseID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(String reviewBy) {
        this.reviewBy = reviewBy;
    }

    public String getReviewByDatabaseID() {
        return reviewByDatabaseID;
    }

    public void setReviewByDatabaseID(String reviewByDatabaseID) {
        this.reviewByDatabaseID = reviewByDatabaseID;
    }
}
