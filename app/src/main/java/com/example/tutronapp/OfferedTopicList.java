package com.example.tutronapp;

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
                // Handle the item click here
                // You can access the complaint object if needed
            });
        }
}}
