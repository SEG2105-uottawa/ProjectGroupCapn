package com.example.tutronapp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PurchaseNotificationTest {

    @Test
    public void testConstructorAndGetters() {
        String studentID = "123";
        String notificationText = "Test notification";

        PurchaseNotification notification = new PurchaseNotification(studentID, notificationText);

        assertEquals(studentID, notification.getStudentID());
        assertEquals(notificationText, notification.getNotificationText());
        assertNull(notification.getDatabaseID());
    }

    @Test
    public void testSettersAndGetters() {
        PurchaseNotification notification = new PurchaseNotification();

        String studentID = "456";
        String notificationText = "Another test notification";
        String databaseID = "789";

        notification.setStudentID(studentID);
        notification.setNotificationText(notificationText);
        notification.setDatabaseID(databaseID);

        assertEquals(studentID, notification.getStudentID());
        assertEquals(notificationText, notification.getNotificationText());
        assertEquals(databaseID, notification.getDatabaseID());
    }
}
