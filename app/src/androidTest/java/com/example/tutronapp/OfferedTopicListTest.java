package com.example.tutronapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.tutronapp.OfferedTopicList;
import com.example.tutronapp.R;
import com.example.tutronapp.Topic;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OfferedTopicListTest {

    @Rule
    public ActivityScenarioRule<TutorHomepageActivity> activityRule = new ActivityScenarioRule<>(TutorHomepageActivity.class);

    private List<Topic> offeredTopicList;

    @Before
    public void setup() {
        // Prepare the data for testing
        offeredTopicList = new ArrayList<>();
        offeredTopicList.add(new Topic("Mathematics", "tutor1", 5, "Algebra and Calculus"));
        offeredTopicList.add(new Topic("Physics", "tutor1", 3, "Mechanics and Thermodynamics"));
        // Set the data to the adapter
        OfferedTopicList adapter = new OfferedTopicList(offeredTopicList);
        activityRule.getScenario().onActivity(activity -> activity.setOfferedTopicsAdapter(adapter));
    }

    @Test
    public void verifyTopicItemDisplayed() {
        // Verify if the topic items are displayed correctly in the RecyclerView
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewOfferedTopics))
                .perform(RecyclerViewActions.scrollToPosition(0))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Mathematics"))))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Algebra and Calculus"))));

        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewOfferedTopics))
                .perform(RecyclerViewActions.scrollToPosition(1))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Physics"))))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Mechanics and Thermodynamics"))));
    }

    @Test
    public void verifyStopOfferingTopic() {
        // Perform a click on the first topic item to stop offering it
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewOfferedTopics))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // Verify if the stop offering dialog is displayed
        Espresso.onView(ViewMatchers.withText("Mathematics"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Choose the "Stop Offering" option
        Espresso.onView(ViewMatchers.withText("Stop Offering"))
                .perform(ViewActions.click());

        // Verify if the topic item is removed from the RecyclerView
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewOfferedTopics))
                .check(ViewAssertions.doesNotExist());
    }
}













