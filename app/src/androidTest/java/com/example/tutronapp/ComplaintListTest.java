package com.example.tutronapp;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ComplaintListTest {

    private List<Complaint> complaintList;

    @Before
    public void setup() {
        complaintList = new ArrayList<>();
        complaintList.add(new Complaint());
        complaintList.add(new Complaint());
        complaintList.add(new Complaint());
        complaintList.add(new Complaint());
        complaintList.add(new Complaint());
        complaintList.add(new Complaint());
        complaintList.add(new Complaint());
        complaintList.add(new Complaint());
        complaintList.add(new Complaint());
        complaintList.add(new Complaint());
    }

    @Test
    public void testComplaintListRecyclerView() {
        ActivityScenario.launch(ManageComplaintsActivity.class);

        // Inflate the layout for the RecyclerView
        View layoutView = LayoutInflater.from(getInstrumentation().getTargetContext())
                .inflate(R.layout.activity_manage_complaints, null);

        // Set up the RecyclerView and the adapter
        RecyclerView recyclerView = layoutView.findViewById(R.id.recyclerViewComplaints);
        ComplaintList adapter = new ComplaintList(complaintList, null);
        recyclerView.setAdapter(adapter);

        // Test RecyclerView item click
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewComplaints))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

    }

    @Test
    public void testComplaintListRecyclerViewItemCount() {
        ActivityScenario<ManageComplaintsActivity> scenario = ActivityScenario.launch(ManageComplaintsActivity.class);

        // Wait for the activity to be idle
        getInstrumentation().waitForIdleSync();

        // Get the RecyclerView from the current activity
        RecyclerView recyclerView = getActivityInstance(scenario).findViewById(R.id.recyclerViewComplaints);

        // Assert the item count of the RecyclerView's adapter
        Assert.assertEquals(complaintList.size(), recyclerView.getAdapter().getItemCount());
    }

    // Helper method to get the current activity instance
    private ManageComplaintsActivity getActivityInstance(ActivityScenario<ManageComplaintsActivity> scenario) {
        final ManageComplaintsActivity[] activityInstance = new ManageComplaintsActivity[1];
        scenario.onActivity(activity -> activityInstance[0] = activity);
        return activityInstance[0];
    }

    @Test
    public void testComplaintListViewHolderDataBinding() {
        // Inflate the layout for the ViewHolder
        View itemView = LayoutInflater.from(getInstrumentation().getTargetContext())
                .inflate(R.layout.layout_complaint_item, null);

        // Create a mock ViewHolder
        ComplaintList.ComplaintViewHolder viewHolder = new ComplaintList.ComplaintViewHolder(itemView);

        // Set up the data for the ViewHolder
        Complaint complaint = new Complaint();
        complaint.setComplaintTitle("Test Complaint");
        complaint.setComplaintAgainst(new Tutor("First Name", "Last Name", "email@example.com", "password",
                "Education Level", "Native Languages", "Short Description"));
        viewHolder.bind(complaint);

        // Test data binding
        TextView titleTextView = itemView.findViewById(R.id.titleTextView);
        TextView againstTextView = itemView.findViewById(R.id.againstTextView);
        Assert.assertEquals("Test Complaint", titleTextView.getText().toString());
        Assert.assertEquals("First Name Last Name", againstTextView.getText().toString());
    }
}