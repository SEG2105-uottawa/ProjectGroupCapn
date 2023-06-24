package com.example.tutronapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonTest {

    @Test
    public void testGetName() {
        Person person = new Person("John", "Doe");
        assertEquals("John Doe", person.getName());
    }

    @Test
    public void testGetFirstName() {
        Person person = new Person("Jane", "Smith");
        assertEquals("Jane", person.getFirstName());
    }

    @Test
    public void setFirstName() {
        Person person = new Person();
        person.setFirstName("Alice");
        assertEquals("Alice", person.getFirstName());
    }

    @Test
    public void getLastName() {
        Person person = new Person("Robert", "Johnson");
        assertEquals("Johnson", person.getLastName());
    }

    @Test
    public void setLastName() {
        Person person = new Person();
        person.setLastName("Brown");
        assertEquals("Brown", person.getLastName());
    }
}