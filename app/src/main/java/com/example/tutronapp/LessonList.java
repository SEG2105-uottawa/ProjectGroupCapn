package com.example.tutronapp;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                builder.setTitle(lesson.getTopicToBeTaught().getTitle())
                        .setItems(new CharSequence[]{"More Information","Submit Review","File Complaint","Close"}, (dialog, which) -> {
                            if (which == 0){
                                StudentHomepageActivity activity = (StudentHomepageActivity) v.getContext();
                                activity.getLessonInformation(lesson);
                            }
                            else if (which == 1){
                                //add review dialog here
                                //call a rate method in StudentHomepageActivity
                                //Rate method should - modify rating, cumulative ratings and numberOfRatings in both Topic
                                //and Tutor. It should also be added to the List<Review> of a topic.
                            }
                            else if (which == 2){
                                dialog.dismiss();
                                //register complaint
                                StudentHomepageActivity activity = (StudentHomepageActivity) v.getContext();
                                AlertDialog.Builder builderOne = new AlertDialog.Builder(itemView.getContext());
                                View dialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.layout_complaint_dialog, null);
                                builderOne.setView(dialogView);

                                EditText editTextComplaintTitle = dialogView.findViewById(R.id.editTextComplaintTitle);
                                EditText editTextComplaintContent = dialogView.findViewById(R.id.editTextComplaintContent);


                                builderOne.setPositiveButton("Submit", (dialogOne, whichOne) -> {
                                    String title = editTextComplaintTitle.getText().toString().trim();
                                    String content = editTextComplaintContent.getText().toString().trim();

                                    if (title.isEmpty() || content.isEmpty()){
                                        Toast.makeText(itemView.getContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                                    }
                                    else if (title.split("\\s+").length > 20) {
                                        Toast.makeText(itemView.getContext(), "Complaint title should have 20 words or less.", Toast.LENGTH_SHORT).show();
                                    }
                                    else if (content.split("\\s+").length > 80) {
                                        Toast.makeText(itemView.getContext(), "Complaint content should have 80 words or less.", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Complaint complaint = new Complaint(lesson.getTutorTeaching(),
                                                activity.getLoggedInStudent(), title, content, "open");

                                        activity.addComplaintToDatabase(complaint);

                                        dialogOne.dismiss();
                                    }
                                });

                                builderOne.setNegativeButton("Cancel", (dialogOne, whichOne) -> dialog.dismiss());

                                AlertDialog dialogOne = builderOne.create();
                                dialogOne.show();
                            }
                            else if (which == 3){
                                dialog.dismiss();
                            }
                        })
                        .show();
                });

        }




    }
}
