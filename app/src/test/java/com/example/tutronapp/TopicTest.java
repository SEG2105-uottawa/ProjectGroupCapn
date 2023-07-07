/* Contains test cases for Topic class
@author Nalan Kurnaz
 */
package com.example.tutronapp;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Test;

public class TopicTest {

    @Test
    public void testEquals() {
        // Create two topics with the same title
        Topic topic1 = new Topic("Math", "1234", 5, "Advanced math topics");
        Topic topic2 = new Topic("Math", "5678", 10, "Math for beginners");

        // Check if the topics are equal
        assertEquals(topic1, topic2);
    }

    @Test
    public void testNotEquals() {
        // Create two topics with different titles
        Topic topic1 = new Topic("Math", "1234", 5, "Advanced math topics");
        Topic topic2 = new Topic("Physics", "5678", 10, "Introduction to physics");

        // Check if the topics are not equal
        assertNotEquals(topic1, topic2);
    }

    @Test
    public void testGettersAndSetters() {
        // Create a topic
        Topic topic = new Topic("Math", "1234", 5, "Advanced math topics");

        // Test the getters and setters
        Assert.assertEquals("Math", topic.getTitle());
        Assert.assertEquals("1234", topic.getTutorDatabaseID());
        Assert.assertEquals(5, topic.getYearsOfExperience());
        Assert.assertEquals("Advanced math topics", topic.getDescription());

        // Modify the topic using setters
        topic.setTitle("Physics");
        topic.setTutorDatabaseID("5678");
        topic.setYearsOfExperience(10);
        topic.setDescription("Introduction to physics");

        // Test the modified values
        Assert.assertEquals("Physics", topic.getTitle());
        Assert.assertEquals("5678", topic.getTutorDatabaseID());
        Assert.assertEquals(10, topic.getYearsOfExperience());
        Assert.assertEquals("Introduction to physics", topic.getDescription());
    }
}
