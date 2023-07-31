/**
 * Pop Up Dialog which shows a PendingPurchasesAdapter
 *
 * @author Abhay A
 */

package com.example.tutronapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PendingPurchasesDialogFragment extends DialogFragment {

    private List<Purchase> pendingPurchases;

    public PendingPurchasesDialogFragment(List<Purchase> pendingPurchases) {
        this.pendingPurchases = pendingPurchases;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pending_purchases_dialog, container, false);

        // Initialize and set up the RecyclerView to display pending purchases
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewPendingPurchases);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Create the adapter for the pending purchases list
        PendingPurchasesAdapter pendingPurchasesAdapter = new PendingPurchasesAdapter(pendingPurchases);
        recyclerView.setAdapter(pendingPurchasesAdapter);

        return rootView;
    }
}

