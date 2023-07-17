package com.example.tutronapp;

import java.io.Serializable;

public class PurchaseNotification implements Serializable {

    private String databaseID;
    private String studentID;
    private String notificationText;

    public PurchaseNotification(){

    }

    public PurchaseNotification(String studentID, String notificationText) {
        this.studentID = studentID;
        this.notificationText = notificationText;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }


    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String databaseID) {
        this.databaseID = databaseID;
    }
}
