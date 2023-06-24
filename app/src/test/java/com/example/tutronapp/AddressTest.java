package com.example.tutronapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddressTest {

    @Test
    public void testGetPostCode() {
        Address address = new Address(123, "Somerset St", "12345" );
        assertEquals("12345", address.getPostCode());
    }

    @Test
    public void testSetPostCode() {
        Address address = new Address();
        address.setPostCode("54321");
        assertEquals("54321", address.getPostCode());
    }

    @Test
    public void getStreetNumber() {
        Address address = new Address(456, "Daly Ave", "K2P2S9");
        assertEquals(456, address.getStreetNumber());
    }

    @Test
    public void setStreetNumber() {
        Address address = new Address();
        address.setStreetNumber(789);
        assertEquals(789, address.getStreetNumber());
    }

    @Test
    public void getStreetName() {
        Address address = new Address(123, "Main St", "06300");
        assertEquals("Main St", address.getStreetName());
    }

    @Test
    public void setStreetName() {
        Address address = new Address();
        address.setStreetName("Elm St");
        assertEquals("Elm St", address.getStreetName());
    }
}