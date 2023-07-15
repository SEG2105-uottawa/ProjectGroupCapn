package com.example.tutronapp;

import java.io.Serializable;

public class Lesson implements Serializable {

    private String databaseID;
    private Topic topicToBeTaught;
    private Long dateOfLesson;
    private Tutor tutorTeaching;
    private String studentDatabaseID;
    private String studentName;

    public Lesson() {

    }

    public Lesson(Topic topicToBeTaught, Long dateOfLesson, Tutor tutorTeaching, String studentDatabaseID) {
        this.topicToBeTaught = topicToBeTaught;
        this.dateOfLesson = dateOfLesson;
        this.tutorTeaching = tutorTeaching;
        this.studentDatabaseID = studentDatabaseID;
    }

    public Lesson(Topic topicToBeTaught, Long dateOfLesson, Tutor tutorTeaching, String studentDatabaseID, String studentName) {
        this.topicToBeTaught = topicToBeTaught;
        this.dateOfLesson = dateOfLesson;
        this.tutorTeaching = tutorTeaching;
        this.studentDatabaseID = studentDatabaseID;
        this.studentName = studentName;
    }

    public Topic getTopicToBeTaught() {
        return topicToBeTaught;
    }

    public void setTopicToBeTaught(Topic topicToBeTaught) {
        this.topicToBeTaught = topicToBeTaught;
    }

    public Long getDateOfLesson() {
        return dateOfLesson;
    }

    public void setDateOfLesson(Long dateOfLesson) {
        this.dateOfLesson = dateOfLesson;
    }

    public String getStudentDatabaseID() {
        return studentDatabaseID;
    }

    public void setStudentDatabaseID(String studentDatabaseID) {
        this.studentDatabaseID = studentDatabaseID;
    }

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String databaseID) {
        this.databaseID = databaseID;
    }

    public Tutor getTutorTeaching() {
        return tutorTeaching;
    }

    public void setTutorTeaching(Tutor tutorTeaching) {
        this.tutorTeaching = tutorTeaching;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
