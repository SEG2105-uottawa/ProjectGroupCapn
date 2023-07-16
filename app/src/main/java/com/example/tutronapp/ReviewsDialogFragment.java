package com.example.tutronapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewsDialogFragment extends DialogFragment {

    private List<Review> reviews;

    public ReviewsDialogFragment(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setNegativeButton("Close", (dialog, which) -> dialog.dismiss());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_reviews, null);

        // Initialize and set up the RecyclerView for the reviews
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewReviews);
        ReviewAdapter adapter = new ReviewAdapter(reviews);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        builder.setView(view);
        return builder.create();
    }
}

