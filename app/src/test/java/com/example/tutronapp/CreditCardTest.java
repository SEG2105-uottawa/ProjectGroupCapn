/*
Contains JUnit Tests for CreditCard
@author Nalan Kurnaz
 */

package com.example.tutronapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CreditCardTest {

    @Test
    public void testGetCardHolder() {
        CreditCard creditCard = new CreditCard("John Doe", 1234567890123456L, 1234, 567);
        assertEquals("John Doe", creditCard.getCardHolder());
    }

    @Test
    public void testGetCardNumber() {
        CreditCard creditCard = new CreditCard("John Doe", 1234567890123456L, 1234, 567);
        assertEquals(1234567890123456L, (long) creditCard.getCardNumber());
    }

    @Test
    public void testGetValidTill() {
        CreditCard creditCard = new CreditCard("John Doe", 1234567890123456L, 1234, 567);
        assertEquals(1234, creditCard.getValidTill());
    }

    @Test
    public void testGetSecurityCode() {
        CreditCard creditCard = new CreditCard("John Doe", 1234567890123456L, 1234, 567);
        assertEquals(567, creditCard.getSecurityCode());
    }

    @Test
    public void testSetCardHolder() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardHolder("Jane Smith");
        assertEquals("Jane Smith", creditCard.getCardHolder());
    }

    @Test
    public void testSetCardNumber() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(9876543210987654L);
        assertEquals(9876543210987654L, (long) creditCard.getCardNumber());
    }

    @Test
    public void testSetValidTill() {
        CreditCard creditCard = new CreditCard();
        creditCard.setValidTill(4321);
        assertEquals(4321, creditCard.getValidTill());
    }

    @Test
    public void testSetSecurityCode() {
        CreditCard creditCard = new CreditCard();
        creditCard.setSecurityCode(765);
        assertEquals(765, creditCard.getSecurityCode());
    }
}