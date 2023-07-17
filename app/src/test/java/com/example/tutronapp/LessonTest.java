package com.example.tutronapp;

import org.junit.Assert;
import org.junit.Test;

public class LessonTest {

    @Test
    public void testEquals() {
        // Create two Lesson objects with the same properties
        Topic topic = new Topic("Math", "tutor1", 5, "Mathematics topic");
        Long date = System.currentTimeMillis();
        Tutor tutor = new Tutor("John", "Doe", "john.doe@example.com", "password");
        String studentId = "123";
        String studentName = "Alice";

        Lesson lesson1 = new Lesson(topic, date, tutor, studentId, studentName);
        Lesson lesson2 = new Lesson(topic, date, tutor, studentId, studentName);

        // Assert that the two Lesson objects are equal
        Assert.assertEquals(lesson1, lesson2);
    }

    @Test
    public void testNotEquals() {
        // Create two Lesson objects with different properties
        Topic topic1 = new Topic("Math", "tutor1", 5, "Mathematics topic");
        Long date1 = System.currentTimeMillis();
        Tutor tutor1 = new Tutor("John", "Doe", "john.doe@example.com", "password");
        String studentId1 = "123";
        String studentName1 = "Alice";

        Topic topic2 = new Topic("Science", "tutor2", 3, "Science topic");
        Long date2 = System.currentTimeMillis() + 1000;
        Tutor tutor2 = new Tutor("Jane", "Smith", "jane.smith@example.com", "password");
        String studentId2 = "456";
        String studentName2 = "Bob";

        Lesson lesson1 = new Lesson(topic1, date1, tutor1, studentId1, studentName1);
        Lesson lesson2 = new Lesson(topic2, date2, tutor2, studentId2, studentName2);

        // Assert that the two Lesson objects are not equal
        Assert.assertNotEquals(lesson1, lesson2);
    }

    @Test
    public void testLessonTutor() {
        // Create a Tutor object
        Tutor tutor = new Tutor("John", "Doe", "john.doe@example.com", "password");

        // Create a Lesson object
        Topic topic = new Topic("Math", "tutor1", 5, "Mathematics topic");
        Long date = System.currentTimeMillis();
        String studentId = "123";
        String studentName = "Alice";
        Lesson lesson = new Lesson(topic, date, tutor, studentId, studentName);

        // Assert that the Tutor in the Lesson is the same as the created Tutor object
        Assert.assertEquals(tutor, lesson.getTutorTeaching());
    }

    @Test
    public void testTutorRating() {
        // Create a Tutor object
        Tutor tutor = new Tutor("John", "Doe", "john.doe@example.com", "password");

        // Add ratings to the Tutor
        tutor.addRating(4);
        tutor.addRating(5);

        // Assert that the Tutor's rating is correctly calculated
        Assert.assertEquals(4.5, tutor.getRating(), 0.01);
    }

    @Test
    public void testTutorRatingWithChange() {
        // Create a Tutor object
        Tutor tutor = new Tutor("John", "Doe", "john.doe@example.com", "password");

        // Add ratings to the Tutor
        tutor.addRating(4);
        tutor.addRating(5);

        // Change a rating
        tutor.changeCurrentRating(4, 3);

        // Assert that the Tutor's rating is correctly updated after the change
        Assert.assertEquals(4.0, tutor.getRating(), 0.01);
    }
}





