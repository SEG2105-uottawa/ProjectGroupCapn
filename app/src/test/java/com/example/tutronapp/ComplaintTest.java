/*
Contains JUnit Tests for Complaint
@author Nalan Kurnaz
 */

package com.example.tutronapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComplaintTest {
    private Tutor tutor;
    private Student student;

    @Before
    public void setUp() {
        // Create a Tutor and Student for testing
        tutor = new Tutor();
        student = new Student();
    }

    @Test
    public void testGettersAndSetters() {
        // Create a Complaint
        Complaint complaint = new Complaint();
        complaint.setComplaintAgainst(tutor);
        complaint.setComplaintBy(student);
        complaint.setComplaintTitle("Test Complaint");
        complaint.setContent("Test Content");
        complaint.setStatus("Pending");

        // Test the getters
        Assert.assertEquals(tutor, complaint.getComplaintAgainst());
        Assert.assertEquals(student, complaint.getComplaintBy());
        Assert.assertEquals("Test Complaint", complaint.getComplaintTitle());
        Assert.assertEquals("Test Content", complaint.getContent());
        Assert.assertEquals("Pending", complaint.getStatus());

        // Test the setters
        Tutor newTutor = new Tutor();
        Student newStudent = new Student();
        complaint.setComplaintAgainst(newTutor);
        complaint.setComplaintBy(newStudent);
        complaint.setComplaintTitle("New Complaint");
        complaint.setContent("New Content");
        complaint.setStatus("Resolved");

        Assert.assertEquals(newTutor, complaint.getComplaintAgainst());
        Assert.assertEquals(newStudent, complaint.getComplaintBy());
        Assert.assertEquals("New Complaint", complaint.getComplaintTitle());
        Assert.assertEquals("New Content", complaint.getContent());
        Assert.assertEquals("Resolved", complaint.getStatus());
    }

    @Test
    public void testGetDatabaseID() {
        // Create a Complaint
        Complaint complaint = new Complaint();
        complaint.setDatabaseID("12345");

        // Test the getDatabaseID() method
        Assert.assertEquals("12345", complaint.getDatabaseID());
    }

    @Test
    public void testSetDatabaseID() {
        // Create a Complaint
        Complaint complaint = new Complaint();

        // Set the databaseID using setDatabaseID() method
        complaint.setDatabaseID("54321");

        // Test the databaseID field directly
        Assert.assertEquals("54321", complaint.getDatabaseID());
    }
}