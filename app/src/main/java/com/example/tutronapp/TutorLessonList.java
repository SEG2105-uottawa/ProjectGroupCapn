package com.example.tutronapp;

import android.annotation.SuppressLint;
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

public class TutorLessonList extends RecyclerView.Adapter<TutorLessonList.TutorLessonViewHolder> {

    private List<Lesson> tutorLessonList;

    public TutorLessonList(List<Lesson> tutorLessonList) {
        this.tutorLessonList = tutorLessonList;
    }

    @NonNull
    @Override
    public TutorLessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_tutor_lesson, parent, false);
        return new TutorLessonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorLessonViewHolder holder, int position) {
        Lesson lesson = tutorLessonList.get(position);
        holder.bind(lesson);
    }

    @Override
    public int getItemCount() {
        return tutorLessonList.size();
    }

    public class TutorLessonViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewLessonTitle;
        private TextView textViewLessonDate;
        private TextView textViewLessonFor;

        public TutorLessonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLessonTitle = itemView.findViewById(R.id.textViewLessonTitle);
            textViewLessonDate = itemView.findViewById(R.id.textViewLessonDate);
            textViewLessonFor = itemView.findViewById(R.id.textViewLessonFor);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Lesson lesson) {
            textViewLessonTitle.setText(lesson.getTopicToBeTaught().getTitle());
            long dateInMillis = lesson.getDateOfLesson();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(new Date(dateInMillis));
            textViewLessonDate.setText("On " + formattedDate);
            textViewLessonFor.setText("For " + lesson.getStudentName());
        }
    }
}
