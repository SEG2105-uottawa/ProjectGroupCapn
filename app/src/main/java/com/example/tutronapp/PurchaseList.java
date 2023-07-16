package com.example.tutronapp;

import android.app.AlertDialog;
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
        try{
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_purchase, parent, false);
            return new PurchaseViewHolder(itemView);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Error inflating the item view.");
        }

    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {
        try{
            if(holder == null){
                throw new IllegalArgumentException("Invalid ViewHolder.");
            }
            Purchase purchase = purchaseList.get(position);
            holder.bind(purchase);
        } catch (Exception e){
            e.printStackTrace();
        }

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
            try {
                textViewPurchaseTitle = itemView.findViewById(R.id.textViewPurchaseTitle);
                textViewPurchaseDate = itemView.findViewById(R.id.textViewPurchaseDate);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Error");
            }
        }

        public void bind(Purchase purchase) {
            try {
                if (purchase == null) {
                    throw new IllegalArgumentException("Invalid Purchase.");
                }
                textViewPurchaseTitle.setText(purchase.getTopicName());
                long dateInMillis = purchase.getDateForLesson();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
                String formattedDate = dateFormat.format(new Date(dateInMillis));
                textViewPurchaseDate.setText("On " + formattedDate);

                itemView.setOnClickListener(v -> {
                    try {
                        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                        builder.setTitle("Purchase Options")
                                .setItems(new CharSequence[]{"Approve Purchase", "Reject Purchase", "Close"}, (dialog, which) -> {
                                    TutorEngagementsActivity activity = (TutorEngagementsActivity) v.getContext();
                                    switch (which) {
                                        case 0: // Approve Purchase
                                            activity.approvePurchase(purchase);
                                            dialog.dismiss();
                                            break;
                                        case 1: // Reject Purchase
                                            activity.rejectPurchase(purchase);
                                            break;
                                        case 2: // Close Dialog
                                            dialog.dismiss();
                                            break;
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                        throw new IllegalArgumentException("The context of the item view is not an instance of TutorEngagementActivity.");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public TextView getTextViewPurchaseTitle() {
            return textViewPurchaseTitle;
        }

        public TextView getTextViewPurchaseDate(){
            return textViewPurchaseDate;
        }
    }
}
