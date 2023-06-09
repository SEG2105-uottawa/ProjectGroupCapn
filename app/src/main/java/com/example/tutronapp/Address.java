package com.example.tutronapp;

import java.io.Serializable;

public class Address implements Serializable {

    private int streetNumber;
    private String streetName;
    private String postCode;
    public Address() {

    }
    public Address(int streetNumber, String streetName, String postCode){
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
