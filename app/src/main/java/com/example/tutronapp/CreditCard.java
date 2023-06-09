package com.example.tutronapp;

import java.io.Serializable;

public class CreditCard implements Serializable {

    private String cardHolder;
    private int cardNumber;
    private int validTill;
    private int securityCode;

    public CreditCard(String cardHolder, int cardNumber, int validTill, int securityCode){

        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.validTill = validTill;
        this.securityCode = securityCode;
    }

    public CreditCard(){

    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getValidTill() {
        return validTill;
    }

    public void setValidTill(int validTill) {
        this.validTill = validTill;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
}
