package com.example.tutronapp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StudentTest {

    private Student student;
    private CreditCard creditCard;
    private Address address;
    private List<Lesson> purchasedLessons;
    private List<Purchase> pendingPurchases;

    @Before
    public void setUp() {
        creditCard = new CreditCard("John Doe", 1234567890L, 2505, 123);
        address = new Address(123, "Street", "12345");
        purchasedLessons = new ArrayList<>();
        pendingPurchases = new ArrayList<>();

        student = new Student("John", "Doe", "john.doe@example.com", "password");
        student.setCreditCard(creditCard);
        student.setAddress(address);
        student.setPurchasedLessons(purchasedLessons);
        student.setPendingPurchases(pendingPurchases);
    }

    @Test
    public void testGetCreditCard() {
        assertEquals(creditCard, student.getCreditCard());
    }

    @Test
    public void testSetCreditCard() {
        CreditCard newCreditCard = new CreditCard("Jane Smith", 9876543210L, 2424, 789);
        student.setCreditCard(newCreditCard);
        assertEquals(newCreditCard, student.getCreditCard());
    }

    @Test
    public void testGetAddress() {
        assertEquals(address, student.getAddress());
    }

    @Test
    public void testSetAddress() {
        Address newAddress = new Address(456, "Avenue", "54321");
        student.setAddress(newAddress);
        assertEquals(newAddress, student.getAddress());
    }

    @Test
    public void testGetPurchasedLessons() {
        assertEquals(purchasedLessons, student.getPurchasedLessons());
    }

    @Test
    public void testSetPurchasedLessons() {
        List<Lesson> newPurchasedLessons = new ArrayList<>();
        newPurchasedLessons.add(new Lesson(new Topic("Mathematics", "tutorID", 2, "Mathematics topic"), 1626421200L, new Tutor(), "studentID"));
        newPurchasedLessons.add(new Lesson(new Topic("Physics", "tutorID", 3, "Physics topic"), 1626424800L, new Tutor(), "studentID"));
        student.setPurchasedLessons(newPurchasedLessons);
        assertEquals(newPurchasedLessons, student.getPurchasedLessons());
    }

    @Test
    public void testGetPendingPurchases() {
        assertEquals(pendingPurchases, student.getPendingPurchases());
    }

    @Test
    public void testSetPendingPurchases() {
        List<Purchase> newPendingPurchases = new ArrayList<>();
        newPendingPurchases.add(new Purchase("studentID", "tutorID", "lessonID", 1626421200L, 1626421200L, true));
        newPendingPurchases.add(new Purchase("studentID", "tutorID", "lessonID", 1626424800L, 1626424800L, true));
        student.setPendingPurchases(newPendingPurchases);
        assertEquals(newPendingPurchases, student.getPendingPurchases());
    }
}

