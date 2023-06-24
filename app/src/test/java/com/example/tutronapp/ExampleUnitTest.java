package com.example.tutronapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    //this is going to test all of the files at the same time

    public static void main(String[] args) {
        TestUtil.runClass(AddressTest.class);
        TestUtil.runClass(AdministratorTest.class);
        TestUtil.runClass(ComplaintListTest.class);
        TestUtil.runClass(ComplaintTest.class);
        TestUtil.runClass(CreditCardTest.class);
        TestUtil.runClass(PersonTest.class);
        TestUtil.runClass(StudentTest.class);
        TestUtil.runClass(TutorTest.class);
        TestUtil.runClass(UserTest.class);
    }


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}