/**
 * Student is a subclass of User class that represents a student in the application.
 * The class provides constructors to initialize the student object, including name, email,
 * password, credit card and address. The class also includes a constant field "role" that specifies
 * as "Student"
 *
 * Getting and setter methods are provided for credit card and address.
 */
package com.example.tutronapp;

import java.io.Serializable;

public class Student extends User{

    private CreditCard creditCard;
    private Address address;


    public Student(String firstName, String lastName, String emailAddress, String password){
        super(firstName,lastName,emailAddress,password, "Student");
    }

    public Student(){

    }

    public Student(String firstName, String lastName, String emailAddress, String password, CreditCard creditCard, Address address){
        super(firstName,lastName,emailAddress,password, "Student");
        this.creditCard = creditCard;
        this.address = address;
    }


    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
