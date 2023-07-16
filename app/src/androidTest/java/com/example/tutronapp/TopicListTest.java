package com.example.tutronapp;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.idling.CountingIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class TopicListTest {

    @Rule
    public ActivityScenarioRule<TutorTopicsActivity> activityRule =
            new ActivityScenarioRule<>(TutorTopicsActivity.class);

    /* IdlingResource used to resolve IllegalStateException
    * CountingIdlingResource used to track the completion of background tasks
    * */
    private CountingIdlingResource idlingResource;


    @Before
    public void setup() {
        idlingResource = new CountingIdlingResource("BackgroundTasks");
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @After
    public void cleanup() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }

    @Test
    public void testRemoveTopic() {
        // Wait for the RecyclerView to be displayed
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewTopics))
                .check(matches(isDisplayed()));

        // Get the initial topic count
        activityRule.getScenario().onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.recyclerViewTopics);
            RecyclerView.Adapter adapter = recyclerView.getAdapter();

            int initialTopicCount = (adapter != null) ? adapter.getItemCount() : 0;

            // Remove a topic by clicking on it
            Espresso.onView(withId(R.id.recyclerViewTopics))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

            // Wait for background tasks to complete
            idlingResource.increment();

            // Verify the topic count has been updated
            Espresso.onView(withId(R.id.recyclerViewTopics)).check((view, noViewFoundException) -> {
                if (noViewFoundException != null) {
                    throw noViewFoundException;
                }

                RecyclerView updatedRecyclerView = (RecyclerView) view;
                RecyclerView.Adapter updatedAdapter = updatedRecyclerView.getAdapter();
                int updatedTopicCount = (updatedAdapter != null) ? updatedAdapter.getItemCount() : 0;
                int expectedUpdatedTopicCount = initialTopicCount - 1;
                assertEquals(expectedUpdatedTopicCount, updatedTopicCount);

                idlingResource.decrement();
            });
        });
    }

    private int getRecyclerViewItemCount(RecyclerView recyclerView) {
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            return recyclerView.getAdapter().getItemCount();
        }
        return 0;
    }
}








