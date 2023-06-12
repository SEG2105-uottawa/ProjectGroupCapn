package com.example.tutronapp;

public class Administrator extends User {


    public Administrator(String firstName, String lastName, String emailAddress, String password){
        super(firstName,lastName,emailAddress,password, "Administrator");
    }

    public Administrator(){

    }
}
