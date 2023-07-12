package com.example.tutronapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchResultList extends RecyclerView.Adapter<SearchResultList.SearchResultViewHolder> {

    private List<Topic> topicList;

    public SearchResultList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_topic_item, parent, false);
        return new SearchResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        Topic topic = topicList.get(position);
        holder.bind(topic);
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public class SearchResultViewHolder extends RecyclerView.ViewHolder {

        private TextView topicNameTextView;
        private TextView yearsOfExperienceTextView;
        private TextView descriptionTextView;

        public SearchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            topicNameTextView = itemView.findViewById(R.id.topicNameTextView);
            //yearsOfExperienceTextView = itemView.findViewById(R.id.yearsOfExperienceTextView);
            descriptionTextView = itemView.findViewById(R.id.topicDescriptionTextView);
        }

        public void bind(Topic topic) {
            topicNameTextView.setText(topic.getTitle());
            descriptionTextView.setText(topic.getDescription());
        }
    }
}

