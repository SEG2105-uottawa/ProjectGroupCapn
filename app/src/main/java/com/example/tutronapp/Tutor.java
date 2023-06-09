/**
 * Tutor is a subclass of User class that represents a tutor in the application.
 * The class provides constructors to initialize the tutor object, including name, email, and
 * password. The class also includes a constant field "role" that specifies as "Tutor"
 */

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
