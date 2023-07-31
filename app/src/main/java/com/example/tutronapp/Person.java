/**
 * Person is a class that represents a person in the application. The class provides constructors to
 * initialize the person object, including first name and last name.
 *
 * Getting and setter methods are provided for the person object.
 *
 * @author Abhay A
 * @author Sum Yan W
 * @author Nalan K
 */

package com.example.tutronapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Person implements Serializable{

    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(){

    }

    public String getName(){return firstName + " " + lastName; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
