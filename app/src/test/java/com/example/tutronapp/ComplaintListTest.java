package com.example.tutronapp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComplaintListTest {

    private ComplaintList complaintList;
    private List<Complaint> complaints;

    @Before
    public void setUp(){
        complaints = new ArrayList<>();
        complaints.add(new Complaint(new Tutor(), new Student(), "Title 1", "Content 1", "Status 1" ));
        complaints.add(new Complaint(new Tutor(), new Student(), "Title 2", "Content 2", "Status 2" ));
        complaints.add(new Complaint(new Tutor(), new Student(), "Title 3", "Content 3", "Status 3" ));

        //creating an instance of ComplaintList
        ComplaintList complaintList = new ComplaintList(complaints);


        //verifying the initial complaint count

        assertEquals(3, complaintList.getItemCount());

        //creating a new list of complaints
        List<Complaint> newComplaints = new ArrayList<>();
        newComplaints.add(new Complaint(new Tutor(), new Student(), "Title 4", "Content 4", "Status 4"));
        newComplaints.add(new Complaint(new Tutor(), new Student(), "Title 5", "Content 5", "Status 5"));

        //setting the new list of complaints
        complaintList.setComplaintList(newComplaints);

        //verifying the new count
        assertEquals(2, complaintList.getItemCount());
    }
}