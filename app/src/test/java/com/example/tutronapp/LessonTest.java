package com.example.tutronapp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LessonTest {

    private Lesson lesson;
    private Topic topic;
    private Tutor tutor;

    @Before
    public void setUp() {
        topic = new Topic("Math", "tutor123", 5, "Algebra");
        tutor = new Tutor("John", "Doe", "john.doe@example.com", "password", "PhD", "English", "Experienced tutor");
        lesson = new Lesson(topic, 1626349200L, tutor, "student123", "Alice");
    }

    @Test
    public void testGetTopicToBeTaught() {
        assertEquals(topic, lesson.getTopicToBeTaught());
    }

    @Test
    public void testGetDateOfLesson() {
        assertEquals(Long.valueOf(1626349200L), lesson.getDateOfLesson());
    }

    @Test
    public void testGetStudentDatabaseID() {
        assertEquals("student123", lesson.getStudentDatabaseID());
    }

    @Test
    public void testGetDatabaseID() {
        assertNull(lesson.getDatabaseID());
    }

    @Test
    public void testSetDatabaseID() {
        lesson.setDatabaseID("lesson1");
        assertEquals("lesson1", lesson.getDatabaseID());
    }

    @Test
    public void testGetTutorTeaching() {
        assertEquals(tutor, lesson.getTutorTeaching());
    }

    @Test
    public void testGetStudentName() {
        assertEquals("Alice", lesson.getStudentName());
    }

    @Test
    public void testSetStudentName() {
        lesson.setStudentName("Bob");
        assertEquals("Bob", lesson.getStudentName());
    }
}



