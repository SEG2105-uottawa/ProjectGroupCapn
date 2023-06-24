package com.example.tutronapp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import org.junit.Test;

public class TutorTest {

    @Test
    public void testConstructorWithParameters() {
        String firstName = "John";
        String lastName = "Doe";
        String emailAddress = "john.doe@example.com";
        String password = "password123";

        Tutor tutor = new Tutor(firstName, lastName, emailAddress, password);

        // Check if the properties are set correctly
        Assert.assertEquals(firstName, tutor.getFirstName());
        Assert.assertEquals(lastName, tutor.getLastName());
        Assert.assertEquals(emailAddress, tutor.getEmailAddress());
        Assert.assertEquals(password, tutor.getPassword());
        Assert.assertEquals("Tutor", tutor.getRole());
    }

    @Test
    public void testSettersAndGetters() {
        Tutor tutor = new Tutor();

        // Set property values
        String firstName = "Jane";
        String lastName = "Smith";
        String emailAddress = "jane.smith@example.com";
        String password = "password456";

        tutor.setFirstName(firstName);
        tutor.setLastName(lastName);
        tutor.setEmailAddress(emailAddress);
        tutor.setPassword(password);

        // Check if the getters return the set values
        Assert.assertEquals(firstName, tutor.getFirstName());
        Assert.assertEquals(lastName, tutor.getLastName());
        Assert.assertEquals(emailAddress, tutor.getEmailAddress());
        Assert.assertEquals(password, tutor.getPassword());
        Assert.assertEquals("Tutor", tutor.getRole());
    }
}

