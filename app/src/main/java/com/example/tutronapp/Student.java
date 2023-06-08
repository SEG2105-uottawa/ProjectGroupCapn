package com.example.tutronapp;

import java.io.Serializable;

public class Student extends User{


    public Student(String firstName, String lastName, String emailAddress, String password){
        super(firstName,lastName,emailAddress,password, "Student");
    }

    public void welcome(){

    }


}
