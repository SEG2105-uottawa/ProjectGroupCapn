package com.example.tutronapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.io.Serializable;
import java.util.List;

public class TopicList extends RecyclerView.Adapter<TopicList.TopicViewHolder> implements Serializable {

    private List<Topic> topicList;

    public TopicList(List<Topic> topicList) {
        this.topicList = topicList;
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
        Topic topic = topicList.get(position);
        holder.bind(topic);
    }

    @Override
    public int getItemCount() {
        return topicList.size();
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
        }
    }
}
