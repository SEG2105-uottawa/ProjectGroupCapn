/**
 * User is a subclass of Person that represents a user in the application.
 * The class provides constructors to initialize the user object(email address, password and role).
 *
 * Getter method are provided for email address, password and role.
 */

package com.example.tutronapp;

public class User extends Person {

    private String emailAddress;
    private String password;
    private String role;

    public User(String firstName, String lastName, String emailAddress, String password, String role) {
        super(firstName, lastName);
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
    }

    public User() {
        // Default constructor required by Firebase
    }

    public Student toStudent(){
        return new Student(this.getFirstName(), this.getLastName(), this.getEmailAddress(), this.getPassword());
    }
    public Tutor toTutor(){
        return new Tutor(this.getFirstName(), this.getLastName(), this.getEmailAddress(), this.getPassword());
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
