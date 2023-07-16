package com.example.tutronapp;

import java.io.Serializable;

public class Review implements Serializable {

    private int rating;
    private String title;
    private String description;
    private Student reviewBy;

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

    public Review(int rating, String title, String description, Student reviewBy) {
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.reviewBy = reviewBy;
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

    public Student getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(Student reviewBy) {
        this.reviewBy = reviewBy;
    }
}
