package com.example.tutronapp;

import java.io.Serializable;

public class Student extends User{

    private CreditCard creditCard;
    private Address address;


    public Student(String firstName, String lastName, String emailAddress, String password){
        super(firstName,lastName,emailAddress,password, "Student");
    }

    public Student(String firstName, String lastName, String emailAddress, String password, CreditCard creditCard, Address address){
        super(firstName,lastName,emailAddress,password, "Student");
        this.creditCard = creditCard;
        this.address = address;
    }

    public void welcome(){

    }


}
