package com.example.tutronapp;
/**
 * A ComplaintList to use as the adapter for the recycler view to be used to show the admin
 * the list of complaints.
 * @author Abhay Ariyappillil
 * @author Sum Yan Wan
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ComplaintList extends RecyclerView.Adapter<ComplaintList.ComplaintViewHolder> {

    private static List<Complaint> complaintList;
    private static OnItemClickListener listener;

    public ComplaintList(List<Complaint> complaintList, OnItemClickListener listener) {
        this.complaintList = complaintList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_complaint_item, parent, false);
        return new ComplaintViewHolder(itemView);
    }

    public interface OnItemClickListener {
        void onItemClick(Complaint complaint);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {
        Complaint complaint = complaintList.get(position);
        // Bind the data to the ViewHolder
        holder.bind(complaint);
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }

    public void setComplaintList(List<Complaint> complaintList) {
        this.complaintList = complaintList;
        notifyDataSetChanged();
    }

    public static class ComplaintViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView againstTextView;

        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views
            titleTextView = itemView.findViewById(R.id.titleTextView);
            againstTextView = itemView.findViewById(R.id.againstTextView);

            // Set click listener for the items
            itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int position = getAdapterPosition();
                   if (position != RecyclerView.NO_POSITION && listener != null) {
                       Complaint clickedComplaint = complaintList.get(position);
                       listener.onItemClick(clickedComplaint);
                   }
               }
            });
        }

        public void bind(Complaint complaint) {
            // Bind the data to the views
            titleTextView.setText(complaint.getComplaintTitle());
            againstTextView.setText(complaint.getComplaintAgainst().getName());

            /**
            // Set click listener for expanding the item
            itemView.setOnClickListener(v -> {
                // Implement the logic to expand the item

            });
             **/
        }
    }
}