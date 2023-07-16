package com.example.tutronapp;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class PurchaseTest {

    @Test
    public void testGettersAndSetters() {
        // Create a purchase
        Purchase purchase = new Purchase("student123", "tutor456", "lesson789", 1625580000000L, 1625623200000L, true);

        // Test the getters and setters
        Assert.assertEquals("student123", purchase.getStudentPurchasingDatabaseID());
        Assert.assertEquals("tutor456", purchase.getTutorTeachingDatabaseID());
        Assert.assertEquals("lesson789", purchase.getLessonTaughtDatabaseID());
        Assert.assertEquals(1625580000000L, purchase.getDateForLesson().longValue());
        Assert.assertEquals(1625623200000L, purchase.getDateOfPurchase().longValue());
        Assert.assertTrue(purchase.isTutorApproved());

        // Modify the purchase using setters
        purchase.setStudentPurchasingDatabaseID("newstudent123");
        purchase.setTutorTeachingDatabaseID("newtutor456");
        purchase.setLessonTaughtDatabaseID("newlesson789");
        purchase.setDateForLesson(1625666400000L);
        purchase.setDateOfPurchase(1625713200000L);
        purchase.setTutorApproved(false);

        // Test the modified values
        Assert.assertEquals("newstudent123", purchase.getStudentPurchasingDatabaseID());
        Assert.assertEquals("newtutor456", purchase.getTutorTeachingDatabaseID());
        Assert.assertEquals("newlesson789", purchase.getLessonTaughtDatabaseID());
        Assert.assertEquals(1625666400000L, purchase.getDateForLesson().longValue());
        Assert.assertEquals(1625713200000L, purchase.getDateOfPurchase().longValue());
        Assert.assertFalse(purchase.isTutorApproved());
    }
}
