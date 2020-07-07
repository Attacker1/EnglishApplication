package com.valera.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.valera.diplom.adapters.LessonsAdapter;
import com.valera.diplom.pojo.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLessons;
    private List<Lesson> lessons = new ArrayList<>();
    private LessonsAdapter adapter;
    public static double countOfBalls0 = 0.0;
    public static double countOfBalls01 = 0.0;
    public static double countOfBalls02 = 0.0;

    public static double countOfBalls1 = 0.0;
    public static double countOfBalls11 = 0.0;
    public static double countOfBalls12 = 0.0;

    public static double countOfBalls2 = 0.0;
    public static double countOfBalls21 = 0.0;
    public static double countOfBalls22 = 0.0;

    public static double countOfBalls3 = 0.0;
    public static double countOfBalls31 = 0.0;
    public static double countOfBalls32 = 0.0;

    public static double countOfBalls4 = 0.0;
    public static double countOfBalls41 = 0.0;
    public static double countOfBalls42 = 0.0;

    public static double countOfBalls5 = 0.0;
    public static double countOfBalls51 = 0.0;
    public static double countOfBalls52 = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        recyclerViewLessons = findViewById(R.id.recyclerViewLessons);
        lessons.add(new Lesson("Урок 1", "Базовая форма глагола",  countOfBalls0));
        lessons.add(new Lesson("Урок 2", "Местоимения. Вопросительные слова", countOfBalls1));
        lessons.add(new Lesson("Урок 3", "Глагол to be. Предлоги места. Like/Want", countOfBalls2));
        lessons.add(new Lesson("Урок 4", "Притяжательные местоимения", countOfBalls3));
        lessons.add(new Lesson("Урок 5", "Профессии. Этикет", countOfBalls4));
        lessons.add(new Lesson("Урок 6", "Степени сравнения прилагательных.\n Указательные местоимения", countOfBalls5));
        adapter = new LessonsAdapter();
        adapter.setLessons(lessons);
        recyclerViewLessons.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLessons.setAdapter(adapter);
        adapter.setOnLessonClickListener(new LessonsAdapter.OnLessonClickListener() {
            @Override
            public void onLessonClick(int position) {
                switch(position){
                    case 0: startActivityLesson(position);
                            break;
                    case 1: if(countOfBalls0 >= 4.5){
                                startActivityLesson(position);
                            }
                            else{
                        Toast.makeText(LessonsActivity.this, "Вы не можете приступить к уроку пока не наберете 4.5 балла за прошлый", Toast.LENGTH_SHORT).show();
                    }
                            break;
                    case 2: if(countOfBalls1 >= 4.5){
                        startActivityLesson(position);
                    }else{
                        Toast.makeText(LessonsActivity.this, "Вы не можете приступить к уроку пока не наберете 4.5 балла за прошлый", Toast.LENGTH_SHORT).show();
                    }
                    case 3: if(countOfBalls2 >= 4.5){
                        startActivityLesson(position);
                    }else{
                        Toast.makeText(LessonsActivity.this, "Вы не можете приступить к уроку пока не наберете 4.5 балла за прошлый", Toast.LENGTH_SHORT).show();
                    }
                    case 4: if(countOfBalls3 >= 4.5){
                        startActivityLesson(position);
                    }else{
                        Toast.makeText(LessonsActivity.this, "Вы не можете приступить к уроку пока не наберете 4.5 балла за прошлый", Toast.LENGTH_SHORT).show();
                    }
                    case 5: if(countOfBalls4 >= 4.5){
                        startActivityLesson(position);
                    }else{
                        Toast.makeText(LessonsActivity.this, "Вы не можете приступить к уроку пока не наберете 4.5 балла за прошлый", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
    private void startActivityLesson(int position){
        Lesson lesson = adapter.getLessons().get(position);
        Intent intent = new Intent(LessonsActivity.this, LessonDetailActivity.class);
        intent.putExtra("description", lesson.getDescription());
        startActivity(intent);
    }
}
