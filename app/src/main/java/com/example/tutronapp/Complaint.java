package com.example.tutronapp;

import java.io.Serializable;

public class Complaint implements Serializable {
    private Tutor complaintAgainst;
    private Student complaintBy;
    private String content;
    private String status;

    public Complaint(){

    }

    public Complaint(Tutor complaintAgainst, Student complaintBy, String content, String status) {
        this.complaintAgainst = complaintAgainst;
        this.complaintBy = complaintBy;
        this.content = content;
        this.status = status;
    }


    public Student getComplaintBy() {
        return complaintBy;
    }

    public void setComplaintBy(Student complaintBy) {
        this.complaintBy = complaintBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tutor getComplaintAgainst() {
        return complaintAgainst;
    }

    public void setComplaintAgainst(Tutor complaintAgainst) {
        this.complaintAgainst = complaintAgainst;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
