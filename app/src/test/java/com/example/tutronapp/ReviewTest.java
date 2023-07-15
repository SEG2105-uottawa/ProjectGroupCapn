package com.example.tutronapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReviewTest {

    @Test
    public void testGettersAndSetters() {
        // Create a sample student
        Student student = new Student("John", "Doe", "john@example.com", "password");


        // Create a review
        Review review = new Review();
        review.setRating(5);
        review.setTitle("Great course");
        review.setDescription("I really enjoyed taking this course from this tutor");
        review.setReviewBy(student);

        // Verify the getters
        assertEquals(5, review.getRating());
        assertEquals("Great course", review.getTitle());
        assertEquals("I really enjoyed taking this course from this tutor", review.getDescription());
        assertEquals(student, review.getReviewBy());

        // Update the review
        review.setRating(4);
        review.setTitle("Good course");
        review.setDescription("It was exciting to learn this course.");
        review.setReviewBy(null);

        // Verify the updated values
        assertEquals(4, review.getRating());
        assertEquals("Good course", review.getTitle());
        assertEquals("It was exciting to learn this course.", review.getDescription());
        assertNull(review.getReviewBy());
    }
}
