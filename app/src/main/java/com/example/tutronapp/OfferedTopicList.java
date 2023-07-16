package com.example.tutronapp;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.io.Serializable;
import java.util.List;

public class OfferedTopicList extends RecyclerView.Adapter<OfferedTopicList.TopicViewHolder> implements Serializable {

    private List<Topic> offeredTopicList;

    public OfferedTopicList(List<Topic> offeredTopicList) {
        this.offeredTopicList = offeredTopicList;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_topic_item, parent, false);
        return new TopicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        Topic topic = offeredTopicList.get(position);
        holder.bind(topic);
    }

    @Override
    public int getItemCount() {
        return offeredTopicList.size();
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {

        private TextView topicNameTextView;
        private TextView yearsOfExperienceTextView;
        private TextView descriptionTextView;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            topicNameTextView = itemView.findViewById(R.id.topicNameTextView);
            descriptionTextView = itemView.findViewById(R.id.topicDescriptionTextView);

        }

        public void bind(Topic topic) {
            topicNameTextView.setText(topic.getTitle());
            descriptionTextView.setText(topic.getDescription());
            itemView.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                builder.setTitle(topic.getTitle())
                        .setItems(new CharSequence[]{"Stop Offering","View Reviews","Close"}, (dialog, which) -> {
                            if (which == 0){
                                TutorTopicsActivity activity = (TutorTopicsActivity) v.getContext();
                                activity.stopOffering(topic);
                            }
                            else if (which == 1){
                                TutorTopicsActivity activity = (TutorTopicsActivity) v.getContext();
                                activity.openReviewsDialogFragment(topic);
                            }
                            else if (which == 2){
                                dialog.dismiss();
                            }
                        })
                        .show();
            });
        }
}}
