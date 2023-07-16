package com.example.tutronapp;

import java.io.Serializable;

public class Purchase implements Serializable {

    private String databaseID;
    private String studentName;
    private String topicName;
    private String tutorName;
    private String studentPurchasingDatabaseID;
    private String tutorTeachingDatabaseID;
    private String lessonTaughtDatabaseID;
    private Long dateForLesson;
    private Long dateOfPurchase;
    private boolean tutorApproved;
    private boolean tutorRejected;


    public Purchase() {
    }

    public Purchase(String studentPurchasingDatabaseID, String tutorTeachingDatabaseID, String lessonTaughtDatabaseID, Long dateForLesson, Long dateOfPurchase, boolean tutorApproved) {
        this.studentPurchasingDatabaseID = studentPurchasingDatabaseID;
        this.tutorTeachingDatabaseID = tutorTeachingDatabaseID;
        this.lessonTaughtDatabaseID = lessonTaughtDatabaseID;
        this.dateForLesson = dateForLesson;
        this.dateOfPurchase = dateOfPurchase;
        this.tutorApproved = tutorApproved;
    }

    public Purchase(String studentName, String topicName, String tutorName, String studentPurchasingDatabaseID, String tutorTeachingDatabaseID, String lessonTaughtDatabaseID, Long dateForLesson, Long dateOfPurchase) {
        this.studentName = studentName;
        this.topicName = topicName;
        this.tutorName = tutorName;
        this.studentPurchasingDatabaseID = studentPurchasingDatabaseID;
        this.tutorTeachingDatabaseID = tutorTeachingDatabaseID;
        this.lessonTaughtDatabaseID = lessonTaughtDatabaseID;
        this.dateForLesson = dateForLesson;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String databaseID) {
        this.databaseID = databaseID;
    }

    public String getStudentPurchasingDatabaseID() {
        return studentPurchasingDatabaseID;
    }

    public void setStudentPurchasingDatabaseID(String studentPurchasingDatabaseID) {
        this.studentPurchasingDatabaseID = studentPurchasingDatabaseID;
    }

    public String getTutorTeachingDatabaseID() {
        return tutorTeachingDatabaseID;
    }

    public void setTutorTeachingDatabaseID(String tutorTeachingDatabaseID) {
        this.tutorTeachingDatabaseID = tutorTeachingDatabaseID;
    }

    public String getLessonTaughtDatabaseID() {
        return lessonTaughtDatabaseID;
    }

    public void setLessonTaughtDatabaseID(String lessonTaughtDatabaseID) {
        this.lessonTaughtDatabaseID = lessonTaughtDatabaseID;
    }

    public Long getDateForLesson() {
        return dateForLesson;
    }

    public void setDateForLesson(Long dateForLesson) {
        this.dateForLesson = dateForLesson;
    }

    public Long getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Long dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public boolean isTutorApproved() {
        return tutorApproved;
    }

    public void setTutorApproved(boolean tutorApproved) {
        this.tutorApproved = tutorApproved;
    }

    public boolean isTutorRejected() {
        return tutorRejected;
    }

    public void setTutorRejected(boolean tutorRejected) {
        this.tutorRejected = tutorRejected;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }
}
