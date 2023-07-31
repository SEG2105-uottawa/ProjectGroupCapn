/**
 * Adapter for Purchases which have not been approved by a tutor
 *
 * @author Abhay A
 */

package com.example.tutronapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PendingPurchasesAdapter extends RecyclerView.Adapter<PendingPurchasesAdapter.PendingPurchaseViewHolder> {

    private List<Purchase> pendingPurchases;

    public PendingPurchasesAdapter(List<Purchase> pendingPurchases) {
        this.pendingPurchases = pendingPurchases;
    }

    @NonNull
    @Override
    public PendingPurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pending_purchase, parent, false);
        return new PendingPurchaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingPurchaseViewHolder holder, int position) {
        Purchase purchase = pendingPurchases.get(position);
        holder.bind(purchase);
    }

    @Override
    public int getItemCount() {
        return pendingPurchases.size();
    }

    public static class PendingPurchaseViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPurchaseTitle;
        private TextView textViewPurchaseDate;
        private TextView textViewPurchaseStatus;
        private TextView textViewPurchaseLessonDate;

        public PendingPurchaseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPurchaseTitle = itemView.findViewById(R.id.textViewPurchaseTitle);
            textViewPurchaseDate = itemView.findViewById(R.id.textViewPurchaseDate);
            textViewPurchaseStatus = itemView.findViewById(R.id.textViewPurchaseStatus);
            textViewPurchaseLessonDate = itemView.findViewById(R.id.textViewPurchaseLessonDate);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Purchase purchase) {
            textViewPurchaseTitle.setText(purchase.getTopicName());
            long dateInMillis = purchase.getDateOfPurchase();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(new Date(dateInMillis));
            textViewPurchaseDate.setText("On " + formattedDate);

            long dateInMillisOne = purchase.getDateForLesson();
            SimpleDateFormat dateFormatOne = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            String formattedDateOne = dateFormatOne.format(new Date(dateInMillisOne));
            textViewPurchaseLessonDate.setText("Lesson Date : " + formattedDateOne);


            boolean tutorApproved = purchase.isTutorApproved();
            boolean tutorRejected = purchase.isTutorRejected();

            if (!tutorApproved && !tutorRejected){
                textViewPurchaseStatus.setText("Pending Tutor Approval");
            }
            else if (!tutorApproved && tutorRejected){
                textViewPurchaseStatus.setText("Purchase Rejected By Tutor");
            }
            else if (tutorApproved && tutorRejected){
                textViewPurchaseStatus.setText("Error, Contact Support");
            }
            else if (tutorApproved && !tutorRejected){
                textViewPurchaseStatus.setText("Purchase approved - Processing");
            }
            else {
                Toast.makeText(itemView.getContext(), "Error: Invalid tutor approval/rejection status", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

