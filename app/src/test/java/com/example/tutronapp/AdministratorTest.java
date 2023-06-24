package com.example.tutronapp;

import static org.junit.Assert.*;

import org.junit.Test;


import org.junit.Assert;
import org.junit.Test;

public class AdministratorTest {

    @Test
    public void testConstructorWithParameters() {
        String firstName = "John";
        String lastName = "Doe";
        String emailAddress = "john.doe@example.com";
        String password = "password123";

        Administrator administrator = new Administrator(firstName, lastName, emailAddress, password);

        // Check if the properties are set correctly
        Assert.assertEquals(firstName, administrator.getFirstName());
        Assert.assertEquals(lastName, administrator.getLastName());
        Assert.assertEquals(emailAddress, administrator.getEmailAddress());
        Assert.assertEquals(password, administrator.getPassword());
        Assert.assertEquals("Administrator", administrator.getRole());
    }

    @Test
    public void testDefaultConstructor() {
        Administrator administrator = new Administrator();

        // Check if the properties are initialized to default values
        Assert.assertNull(administrator.getFirstName());
        Assert.assertNull(administrator.getLastName());
        Assert.assertNull(administrator.getEmailAddress());
        Assert.assertNull(administrator.getPassword());
        Assert.assertNull(administrator.getRole());
    }

    @Test
    public void testSettersAndGetters() {
        Administrator administrator = new Administrator();

        // Set property values
        String firstName = "Jane";
        String lastName = "Smith";
        String emailAddress = "jane.smith@example.com";
        String password = "password456";
        String role = "Administrator";

        administrator.setFirstName(firstName);
        administrator.setLastName(lastName);
        administrator.setEmailAddress(emailAddress);
        administrator.setPassword(password);
        administrator.setRole(role);

        // Check if the getters return the set values
        Assert.assertEquals(firstName, administrator.getFirstName());
        Assert.assertEquals(lastName, administrator.getLastName());
        Assert.assertEquals(emailAddress, administrator.getEmailAddress());
        Assert.assertEquals(password, administrator.getPassword());
        Assert.assertEquals(role, administrator.getRole());
    }
}