package com.valera.diplom.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.valera.diplom.pojo.Lesson;
import com.valera.diplom.R;

import java.util.ArrayList;
import java.util.List;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.LessonsViewHolder> {

    private List<Lesson> lessons;
    private OnLessonClickListener onLessonClickListener;

    public void setOnLessonClickListener(OnLessonClickListener onLessonClickListener) {
        this.onLessonClickListener = onLessonClickListener;
    }

    public LessonsAdapter() {
        lessons = new ArrayList<>();
    }

    public interface OnLessonClickListener{
        void onLessonClick(int position);
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
        notifyDataSetChanged();
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    @NonNull
    @Override
    public LessonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_lesson, parent, false);
        return new LessonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        holder.textViewTitle.setText(lesson.getTitle());
        holder.textViewDescription.setText(lesson.getDescription());
        holder.textViewCountOfBalls.setText("" + lesson.getCountOfBalls());
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    class LessonsViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewCountOfBalls;

        public LessonsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewCountOfBalls = itemView.findViewById(R.id.textViewCountOfBalls);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onLessonClickListener != null){
                        onLessonClickListener.onLessonClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
