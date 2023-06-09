package com.example.tutronapp;

public class Address {

    private String postCode;
    private int streetNumber;
    private String streetName;
    public Address() {

    }
    public Address(String postCode, int streetNumber, String streetName){
        this.postCode = postCode;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
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
