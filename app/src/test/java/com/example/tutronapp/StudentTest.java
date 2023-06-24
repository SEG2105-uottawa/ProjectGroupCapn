package com.example.tutronapp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StudentTest {
    private Student student;

    @Before
    public void setUp(){
      //creating a new student object for testing

      student = new Student("Joyce", "Newman", "joyce@joymail.com", "password");
    }

    @Test
    public void testGetFirstName(){
        assertEquals("Joyce", student.getFirstName());
    }

    @Test
    public void testGetLastName(){
        assertEquals("Newman", student.getLastName());
    }

    @Test
    public void testGetEmailAddress() {
        assertEquals("joyce@joymail.com", student.getEmailAddress());
    }

    @Test
    public void testGetPassword(){
        assertEquals("password", student.getPassword());
    }

    @Test
    public void testGetRole(){
        assertEquals("Student", student.getRole());
    }

    @Test
    public void testGetCreditCard() {
        assertNull(student.getCreditCard()); // Initial value should be null
    }

    @Test
    public void testSetCreditCard() {
        CreditCard creditCard = new CreditCard("John Doe", 1234567890123456L, 1234, 567);
        student.setCreditCard(creditCard);
        assertEquals(creditCard, student.getCreditCard());
    }

    @Test
    public void testGetAddress() {
        assertNull(student.getAddress()); // Initial value should be null
    }

    @Test
    public void testSetAddress() {
        Address address = new Address(123, "Main Street", "12345");
        student.setAddress(address);
        assertEquals(address, student.getAddress());
    }

}