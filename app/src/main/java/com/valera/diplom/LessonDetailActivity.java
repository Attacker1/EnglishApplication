package com.valera.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.valera.diplom.Lessons.DictionaryActivity;
import com.valera.diplom.Lessons.GrammarActivity;
import com.valera.diplom.Lessons.ListeningActivity;
import com.valera.diplom.adapters.LessonsAdapter;
import com.valera.diplom.pojo.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerViewAttributes;
    private LessonsAdapter attributesAdapter;
    private List<Lesson> attributes = new ArrayList<>();
    private TextView textViewTitle;
    private CardView cardViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);
        recyclerViewAttributes = findViewById(R.id.recyclerViewAttributes);
        attributesAdapter = new LessonsAdapter();
        textViewTitle = findViewById(R.id.textViewDescription);
        cardViewDescription = findViewById(R.id.cardViewDescription);
        cardViewDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonDetailActivity.this, LessonDescriptionActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("description")){
            String description = intent.getStringExtra("description");
            textViewTitle.setText(description);
        }
        else{
            finish();
        }

        attributes.add(new Lesson("Начать урок", LessonsActivity.countOfBalls0));
        attributes.add(new Lesson("Новые слова", "Учить новые слова", LessonsActivity.countOfBalls01));
        attributes.add(new Lesson("Аудирование","Тренировка английской речи на слух", LessonsActivity.countOfBalls02));
        attributesAdapter.setLessons(attributes);
        recyclerViewAttributes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAttributes.setAdapter(attributesAdapter);
        attributesAdapter.setOnLessonClickListener(new LessonsAdapter.OnLessonClickListener() {
            @Override
            public void onLessonClick(int position) {
                Intent intent;
                switch(position){
                    case 0:
                        intent = new Intent(LessonDetailActivity.this, GrammarActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(LessonDetailActivity.this, DictionaryActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(LessonDetailActivity.this, ListeningActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

    }
}
