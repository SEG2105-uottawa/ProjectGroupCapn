package com.example.tutronapp;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ReviewAdapterTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testRecyclerViewItems() {
        // Create a list of dummy review data
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(4, "Great Review 1", "This is the first review."));
        reviews.add(new Review(5, "Awesome Review 2", "This is the second review."));
        reviews.add(new Review(3, "Good Review 3", "This is the third review."));

        // Set up the RecyclerView adapter
        ReviewAdapter adapter = new ReviewAdapter(reviews);
        ActivityScenario<MainActivity> scenario = activityScenarioRule.getScenario();
        scenario.onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.recyclerViewReviews);
            recyclerView.setAdapter(adapter);
        });

        // Perform assertions on the RecyclerView items
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewReviews))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check((view, noViewFoundException) -> {
                    RecyclerView recyclerView = (RecyclerView) view;
                    RecyclerView.Adapter adapterUnderTest = recyclerView.getAdapter();
                    assert adapterUnderTest != null;
                    assert adapterUnderTest.getItemCount() == 3;

                    // Assert the expected values of the first item in the RecyclerView
                    Espresso.onView(withText("4 Stars"))
                            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
                    Espresso.onView(withText("Great Review 1"))
                            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
                    Espresso.onView(withText("This is the first review."))
                            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
                });
    }
}

