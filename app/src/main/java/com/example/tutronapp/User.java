package com.example.tutronapp;

public class User extends Person{

    private String emailAddress;
    private String password;

    public User(String firstName, String lastName, String emailAddress, String password){
        super(firstName,lastName);
        this.emailAddress = emailAddress;
        this.password = password;
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
}
