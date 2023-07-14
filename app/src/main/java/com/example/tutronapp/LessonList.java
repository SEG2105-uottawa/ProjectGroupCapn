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

public class LessonList extends RecyclerView.Adapter<LessonList.LessonViewHolder> {

    private List<Lesson> lessons;

    public LessonList(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
        return new LessonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        holder.bind(lesson);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public class LessonViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTutorName;
        private TextView textViewLessonTitle;
        private TextView textViewLessonDate;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLessonTitle = itemView.findViewById(R.id.textViewLessonTitle);
            textViewLessonDate = itemView.findViewById(R.id.textViewLessonDate);
            textViewTutorName = itemView.findViewById(R.id.textViewTutorName);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Lesson lesson) {
            textViewLessonTitle.setText(lesson.getTopicToBeTaught().getTitle());
            long dateInMillis = lesson.getDateOfLesson();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(new Date(dateInMillis));
            textViewLessonDate.setText("On " + formattedDate);
            textViewTutorName.setText("By " + lesson.getTutorTeaching().getName());

            itemView.setOnClickListener(v ->{
                StudentHomepageActivity activity = (StudentHomepageActivity) v.getContext();
                activity.getLessonInformation(lesson);

            });
        }
    }
}
