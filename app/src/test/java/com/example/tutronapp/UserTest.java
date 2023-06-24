package com.example.tutronapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

    @Test
    public void testToStudent() {
        User user = new User("John", "Doe", "john.doe@example.com", "password", "student");
        Student student = user.toStudent();
        assertEquals("John Doe", student.getName());
        assertEquals("john.doe@example.com", student.getEmailAddress());
        assertEquals("password", student.getPassword());
    }

    @Test
    public void testToTutor() {
        User user = new User("Jane", "Smith", "jane.smith@example.com", "password", "tutor");
        Tutor tutor = user.toTutor();
        assertEquals("Jane Smith", tutor.getName());
        assertEquals("jane.smith@example.com", tutor.getEmailAddress());
        assertEquals("password", tutor.getPassword());
    }

    @Test
    public void toAdministrator() {
        User user = new User("Robert", "Johnson", "robert.johnson@example.com", "password", "administrator");
        Administrator administrator = user.toAdministrator();
        assertEquals("Robert Johnson", administrator.getName());
        assertEquals("robert.johnson@example.com", administrator.getEmailAddress());
        assertEquals("password", administrator.getPassword());
    }

    @Test
    public void testGetDataBaseID() {
        User user = new User();
        user.setDataBaseID("123456");
        assertEquals("123456", user.getDataBaseID());
    }

    @Test
    public void testSetDataBaseID() {
        User user = new User();
        user.setDataBaseID("654321");
        assertEquals("654321", user.getDataBaseID());
    }

    @Test
    public void testGetEmailAddress() {
        User user = new User("Alice", "Brown", "alice.brown@example.com", "password", "student");
        assertEquals("alice.brown@example.com", user.getEmailAddress());
    }

    @Test
    public void testSetEmailAddress() {
        User user = new User();
        user.setEmailAddress("test@example.com");
        assertEquals("test@example.com", user.getEmailAddress());
    }

    @Test
    public void testGetPassword() {
        User user = new User("John", "Doe", "john.doe@example.com", "password", "student");
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        User user = new User();
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testGetRole() {
        User user = new User("Jane", "Smith", "jane.smith@example.com", "password", "tutor");
        assertEquals("tutor", user.getRole());
    }

    @Test
    public void testSetRole() {
        User user = new User();
        user.setRole("administrator");
        assertEquals("administrator", user.getRole());
    }

}