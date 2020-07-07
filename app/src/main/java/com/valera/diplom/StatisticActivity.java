package com.valera.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class StatisticActivity extends AppCompatActivity {
    private TextView textViewWordsCount;
    private TextView textViewGrammarCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        textViewWordsCount = findViewById(R.id.textViewWordsCount);
        textViewGrammarCount = findViewById(R.id.textViewGrammarCount);
        textViewWordsCount.setText(LessonsActivity.countOfBalls01 + "");
        textViewGrammarCount.setText(LessonsActivity.countOfBalls0 + "");
    }
}
