package com.example.tutronapp;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.tutronapp.OfferedTopicList;
import com.example.tutronapp.R;
import com.example.tutronapp.Topic;
import com.example.tutronapp.TutorHomepageActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class OfferedTopicListTest {

    @Rule
    public ActivityScenarioRule<TutorHomepageActivity> activityScenarioRule =
            new ActivityScenarioRule<>(TutorHomepageActivity.class);

    @Test
    public void clickOnTopic_DisplaysAlertDialog() {
        // Create a list of offered topics
        List<Topic> offeredTopics = new ArrayList<>();
        Topic topic1 = new Topic("Mathematics", "Tutor1", 5, "Math topic description");
        Topic topic2 = new Topic("Science", "Tutor1", 3, "Science topic description");
        offeredTopics.add(topic1);
        offeredTopics.add(topic2);

        // Get the activity instance
        TutorHomepageActivity activity = getActivityInstance();

        // Set the offered topics in the adapter
        OfferedTopicList offeredTopicList = new OfferedTopicList(offeredTopics);

        // Set the adapter to the RecyclerView in TutorHomepageActivity
        activity.setOfferedTopicsAdapter(offeredTopicList);

        // Perform a click on the first topic in the list
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewOfferedTopics))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // Verify that the AlertDialog is displayed
        Espresso.onView(withText("Mathematics"))
                .check(matches(isDisplayed()));
    }

    private TutorHomepageActivity getActivityInstance() {
        final TutorHomepageActivity[] activity = new TutorHomepageActivity[1];
        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<TutorHomepageActivity>() {
            @Override
            public void perform(TutorHomepageActivity activityInstance) {
                activity[0] = activityInstance;
            }
        });
        return activity[0];
    }
}












