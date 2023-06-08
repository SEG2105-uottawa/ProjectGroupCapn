package com.example.tutronapp;

public class Tutor extends User{

    private final String role = "Tutor";

    public Tutor(String firstName, String lastName, String emailAddress, String password) {
        super(firstName, lastName, emailAddress, password,"Tutor");
    }

    public String getRole() {
        return role;
    }
}
