package com.example.tutronapp;

import org.junit.Assert;
import org.junit.Test;

public class ReviewTest {

    @Test
    public void testGetRating() {
        int expectedRating = 5;
        Review review = new Review(expectedRating);

        int actualRating = review.getRating();

        Assert.assertEquals(expectedRating, actualRating);
    }

    @Test
    public void testSetRating() {
        int expectedRating = 3;
        Review review = new Review();

        review.setRating(expectedRating);
        int actualRating = review.getRating();

        Assert.assertEquals(expectedRating, actualRating);
    }

    @Test
    public void testGetTitle() {
        String expectedTitle = "Sample Title";
        Review review = new Review(4, expectedTitle, "Sample Description");

        String actualTitle = review.getTitle();

        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testSetTitle() {
        String expectedTitle = "New Title";
        Review review = new Review();

        review.setTitle(expectedTitle);
        String actualTitle = review.getTitle();

        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetDescription() {
        String expectedDescription = "Sample Description";
        Review review = new Review(4, "Sample Title", expectedDescription);

        String actualDescription = review.getDescription();

        Assert.assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testSetDescription() {
        String expectedDescription = "New Description";
        Review review = new Review();

        review.setDescription(expectedDescription);
        String actualDescription = review.getDescription();

        Assert.assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testGetReviewBy() {
        String expectedReviewBy = "John Doe";
        Review review = new Review(4, "Sample Title", "Sample Description");
        review.setReviewBy(expectedReviewBy);

        String actualReviewBy = review.getReviewBy();

        Assert.assertEquals(expectedReviewBy, actualReviewBy);
    }

    @Test
    public void testSetReviewBy() {
        String expectedReviewBy = "Jane Smith";
        Review review = new Review();

        review.setReviewBy(expectedReviewBy);
        String actualReviewBy = review.getReviewBy();

        Assert.assertEquals(expectedReviewBy, actualReviewBy);
    }

    @Test
    public void testGetReviewByDatabaseID() {
        String expectedReviewByDatabaseID = "12345";
        Review review = new Review(4, "Sample Title", "Sample Description");
        review.setReviewByDatabaseID(expectedReviewByDatabaseID);

        String actualReviewByDatabaseID = review.getReviewByDatabaseID();

        Assert.assertEquals(expectedReviewByDatabaseID, actualReviewByDatabaseID);
    }

    @Test
    public void testSetReviewByDatabaseID() {
        String expectedReviewByDatabaseID = "67890";
        Review review = new Review();

        review.setReviewByDatabaseID(expectedReviewByDatabaseID);
        String actualReviewByDatabaseID = review.getReviewByDatabaseID();

        Assert.assertEquals(expectedReviewByDatabaseID, actualReviewByDatabaseID);
    }
}

