package com.example.tutronapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopicList extends RecyclerView.Adapter<TopicList.TopicViewHolder> {

    private List<String> topicList;

    public TopicList(List<String> topicList) {
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
        String topicName = topicList.get(position);
        holder.bind(topicName);
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {

        private TextView topicNameTextView;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            topicNameTextView = itemView.findViewById(R.id.topicNameTextView);
        }

        public void bind(String topicName) {
            topicNameTextView.setText(topicName);
        }
    }
}

