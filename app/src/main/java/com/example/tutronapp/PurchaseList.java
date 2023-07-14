package com.example.tutronapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PurchaseList extends RecyclerView.Adapter<PurchaseList.PurchaseViewHolder> {

    private List<Purchase> purchaseList;

    public PurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_purchase, parent, false);
        return new PurchaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {
        Purchase purchase = purchaseList.get(position);
        holder.bind(purchase);
    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }

    public class PurchaseViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewPurchaseTitle;
        private TextView textViewPurchaseDate;

        public PurchaseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPurchaseTitle = itemView.findViewById(R.id.textViewPurchaseTitle);
            textViewPurchaseDate = itemView.findViewById(R.id.textViewPurchaseDate);
        }

        public void bind(Purchase purchase) {
            textViewPurchaseTitle.setText(purchase.getTopicName());
            long dateInMillis = purchase.getDateForLesson();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(new Date(dateInMillis));
            textViewPurchaseDate.setText("On " + formattedDate);

            itemView.setOnClickListener(v -> {

            });
        }
    }
}
