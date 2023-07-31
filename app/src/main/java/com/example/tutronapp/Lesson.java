/**
 * Representation of a Lesson
 * A lesson is a Topic which has been purchased by a
 * student
 * @author Abhay A
 */

package com.example.tutronapp;

import java.io.Serializable;
import java.util.Objects;

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


    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Lesson otherLesson = (Lesson) object;

        if (otherLesson.getDateOfLesson().longValue() == (this.dateOfLesson.longValue())){
            if (otherLesson.studentDatabaseID.equals(this.studentDatabaseID)){
                if (otherLesson.getTopicToBeTaught().getTitle().equals(this.topicToBeTaught.getTitle())){
                    return true;
                }
            }
        }

        return false;
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
