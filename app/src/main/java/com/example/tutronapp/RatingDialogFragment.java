package com.example.tutronapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class RatingDialogFragment extends DialogFragment {

    private OnRatingCompleteListener listener;

    // Interface for the callback
    public interface OnRatingCompleteListener {
        void onRatingComplete(float rating, String title, String description);
    }

    public void setOnRatingCompleteListener(OnRatingCompleteListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Build your custom dialog here
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.dialog_rate_lesson);

        RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
        EditText editTextTitle = dialog.findViewById(R.id.editTextReviewTitle);
        EditText editTextDescription = dialog.findViewById(R.id.editTextReviewDescription);

        dialog.findViewById(R.id.btnOk).setOnClickListener(v -> {
            // Retrieve the values from the views
            float rating = ratingBar.getRating();
            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();
            if (listener != null) {
                listener.onRatingComplete(rating, title, description);
            }
            dismiss();
        });

        return dialog;
    }
}

