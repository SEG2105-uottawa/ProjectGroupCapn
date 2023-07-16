package com.example.tutronapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviews;

    public ReviewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try{
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
            return new ReviewViewHolder(itemView);
        } catch(Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Error inflating the item view. Please check the layout resource.");
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        try{
            if(holder == null){
                throw new IllegalArgumentException("Invalid ViewHolder");
            }
            Review review = reviews.get(position);
            holder.bind(review);
        } catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewRating;
        private TextView textViewTitle;
        private TextView textViewDescription;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            try{
                textViewRating = itemView.findViewById(R.id.textViewRating);
                textViewTitle = itemView.findViewById(R.id.textViewTitle);
                textViewDescription = itemView.findViewById(R.id.textViewDescription);
            } catch(Exception e){
                e.printStackTrace();
                throw new IllegalArgumentException("Error");
            }

        }

        @SuppressLint("SetTextI18n")
        public void bind(Review review) {
            try{
                if(review == null){
                    throw new IllegalArgumentException("Invalid Input");
                }
                textViewRating.setText(String.valueOf(review.getRating()) + " Stars");
                textViewTitle.setText(review.getTitle());
                textViewDescription.setText(review.getDescription());
            } catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}
