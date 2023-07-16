package com.example.tutronapp;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class PurchaseListTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testRecyclerViewItems() {
        // Create a list of dummy purchase data
        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(new Purchase("Purchase 1", "Topic 1", "Tutor 1", "student_id_1", "tutor_id_1", "lesson_id_1", new Date().getTime(), new Date().getTime()));
        purchaseList.add(new Purchase("Purchase 2", "Topic 2", "Tutor 2", "student_id_2", "tutor_id_2", "lesson_id_2", new Date().getTime(), new Date().getTime()));
        purchaseList.add(new Purchase("Purchase 3", "Topic 3", "Tutor 3", "student_id_3", "tutor_id_3", "lesson_id_3", new Date().getTime(), new Date().getTime()));

        // Set up the RecyclerView adapter
        PurchaseList adapter = new PurchaseList(purchaseList);
        activityScenarioRule.getScenario().onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.recyclerViewYourLessons);
            recyclerView.setAdapter(adapter);
        });

        // Perform assertions on the RecyclerView items
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewYourLessons))
                .perform(RecyclerViewActions.scrollToPosition(0))
                .check((view, noViewFoundException) -> {
                    RecyclerView recyclerView = (RecyclerView) view;
                    RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(0);
                    if (viewHolder != null && viewHolder instanceof PurchaseList.PurchaseViewHolder) {
                        PurchaseList.PurchaseViewHolder purchaseViewHolder = (PurchaseList.PurchaseViewHolder) viewHolder;
                        // Assert the expected values of the first item in the RecyclerView
                        assert purchaseViewHolder.getTextViewPurchaseDate().getText().toString().equals("Purchase 1");
                        assert purchaseViewHolder.getTextViewPurchaseDate().getText().toString().equals("On " + purchaseList.get(0).getFormattedDate());
                    }
                });
    }
}



