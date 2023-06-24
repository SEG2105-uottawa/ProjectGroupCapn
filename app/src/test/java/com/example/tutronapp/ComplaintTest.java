package com.example.tutronapp;

import org.junit.Assert;
import org.junit.Test;

public class ComplaintTest {

    @Test
    public void testConstructorWithParameters() {
        Tutor complaintAgainst = new Tutor();
        Student complaintBy = new Student();
        String complaintTitle = "Late Submission";
        String content = "The tutor submitted the assignment late.";
        String status = "Open";

        Complaint complaint = new Complaint(complaintAgainst, complaintBy, complaintTitle, content, status);

        // Check if the properties are set correctly
        Assert.assertEquals(complaintAgainst, complaint.getComplaintAgainst());
        Assert.assertEquals(complaintBy, complaint.getComplaintBy());
        Assert.assertEquals(complaintTitle, complaint.getComplaintTitle());
        Assert.assertEquals(content, complaint.getContent());
        Assert.assertEquals(status, complaint.getStatus());
    }

    @Test
    public void testSettersAndGetters() {
        Complaint complaint = new Complaint();

        // Set property values
        Tutor complaintAgainst = new Tutor();
        Student complaintBy = new Student();
        String complaintTitle = "Inappropriate Behavior";
        String content = "The tutor exhibited inappropriate behavior during the session.";
        String status = "Closed";

        complaint.setComplaintAgainst(complaintAgainst);
        complaint.setComplaintBy(complaintBy);
        complaint.setComplaintTitle(complaintTitle);
        complaint.setContent(content);
        complaint.setStatus(status);

        // Check if the getters return the set values
        Assert.assertEquals(complaintAgainst, complaint.getComplaintAgainst());
        Assert.assertEquals(complaintBy, complaint.getComplaintBy());
        Assert.assertEquals(complaintTitle, complaint.getComplaintTitle());
        Assert.assertEquals(content, complaint.getContent());
        Assert.assertEquals(status, complaint.getStatus());
    }

    @Test
    public void testDatabaseID() {
        Complaint complaint = new Complaint();

        // Set and get database ID
        String databaseID = "12345";
        complaint.setDatabaseID(databaseID);
        String retrievedDatabaseID = complaint.getDatabaseID();

        // Check if the database ID is set and retrieved correctly
        Assert.assertEquals(databaseID, retrievedDatabaseID);
    }
}