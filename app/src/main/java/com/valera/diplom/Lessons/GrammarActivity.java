package com.valera.diplom.Lessons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.valera.diplom.LessonsActivity;
import com.valera.diplom.R;
import com.valera.diplom.SentencesDatabase;
import com.valera.diplom.pojo.GrammarSentence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GrammarActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewOpinion0;
    private TextView textViewOpinion1;
    private TextView textViewOpinion2;
    private TextView textViewOpinion3;
    private TextView textViewOpinion4;
    private TextView textViewOpinion5;
    private TextView textViewOpinion6;
    private TextView textViewOpinion7;
    private TextView textViewAnswerLine;
    private TextView textViewResult;
    private TextView textViewScore;

    private Random random;
    private StringBuilder builder;
    private SentencesDatabase database;
    private ArrayList<GrammarSentence> sentences;
    private ArrayList<TextView> options = new ArrayList<>(); // список поций для выбора
    private ArrayList<String> wrongAnswers = new ArrayList<>(); //список неправильных ответов
    private int rightExamplePosition;
    private String rightTranslation;
    private String rightSentence;
    private String currentSentence;
    private int countOfRightAnswers = 0;
    private int countOfQuestions = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        database = SentencesDatabase.getInstance(this);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewOpinion0 = findViewById(R.id.textViewOpinion0);
        textViewOpinion1 = findViewById(R.id.textViewOpinion1);
        textViewOpinion2 = findViewById(R.id.textViewOpinion2);
        textViewOpinion3 = findViewById(R.id.textViewOpinion3);
        textViewOpinion4 = findViewById(R.id.textViewOpinion4);
        textViewOpinion5 = findViewById(R.id.textViewOpinion5);
        textViewOpinion6 = findViewById(R.id.textViewOpinion6);
        textViewOpinion7 = findViewById(R.id.textViewOpinion7);
        textViewAnswerLine = findViewById(R.id.textViewAnswerLine);
        textViewScore = findViewById(R.id.textViewScore);
        sentences = new ArrayList<>();
        options.add(textViewOpinion0);
        options.add(textViewOpinion1);
        options.add(textViewOpinion2);
        options.add(textViewOpinion3);
        options.add(textViewOpinion4);
        options.add(textViewOpinion5);
        options.add(textViewOpinion6);
        options.add(textViewOpinion7);
        builder = new StringBuilder();
        random = new Random();
        getData();
        addSentences();
        addWrongAnswers();
        playNext();
    }

    private void playNext(){
        generateQuestion();
        String[] sentence = disassembleSentence(sentences.get(rightExamplePosition).getSentence());
        for(int i=0; i < options.size(); i++){
            if(sentence.length > i){
                options.get(i).setText(sentence[i]);
            }
            else{
                options.get(i).setText(generateWrongAnswer());
            }
        }
        String score = String.format("%s / %s", countOfRightAnswers, countOfQuestions);
        textViewScore.setText(score);
    }

    private void generateQuestion(){
        rightExamplePosition = random.nextInt(sentences.size());
        GrammarSentence question = sentences.get(rightExamplePosition);
        rightTranslation = question.getTranslation();
        rightSentence = question.getSentence();
        textViewQuestion.setText(rightTranslation);
    }

    private String generateWrongAnswer() {
        String result = wrongAnswers.get(random.nextInt(wrongAnswers.size()));
        return result;
    }

    public void onClickAnswer(View view) {
        TextView textView = (TextView) view;
        String chosenAnswer = textView.getText().toString();
        builder.append(chosenAnswer + " ");
        textViewAnswerLine.setText(builder.toString());
        textView.setText("");
        if (textViewAnswerLine.getText().toString().trim().equals(rightSentence.trim())){
            Toast.makeText(this, "Верно!", Toast.LENGTH_SHORT).show();
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textViewResult.setVisibility(View.VISIBLE);
            textViewResult.setTextColor(Color.GREEN);
            textViewResult.setText("Верно!");
            textViewResult.setVisibility(View.INVISIBLE);*/
            builder.setLength(0);
            textViewAnswerLine.setText("Переведите предложение");
            countOfRightAnswers++;
            countOfQuestions++;
            LessonsActivity.countOfBalls0 += 0.1;
            playNext();
        }
        if(disassembleSentence(textViewAnswerLine.getText().toString()).length > disassembleSentence(rightSentence).length){
            Toast.makeText(this, "Не верно, правильный ответ: " + rightSentence, Toast.LENGTH_LONG).show();
            /*textViewResult.setVisibility(View.VISIBLE);
            textViewResult.setTextColor(Color.RED);
            textViewResult.setText("Не верно, правильный ответ: " + rightSentence);

            textViewResult.setVisibility(View.INVISIBLE);*/
            builder.setLength(0);
            textViewAnswerLine.setText("Переведите предложение");
            countOfQuestions++;
            if(LessonsActivity.countOfBalls0 > 0){
                LessonsActivity.countOfBalls0 -=0.1;
            }
            playNext();
        }
        Log.i("TESTING", " " + sentences.size());
    }

    private void getData(){
        LiveData<List<GrammarSentence>> sentencesFromDB = database.grammarSentencesDao().getAllSentences();
        sentencesFromDB.observe(this, new Observer<List<GrammarSentence>>() {
            @Override
            public void onChanged(List<GrammarSentence> grammarSentencesFromLiveData) {
                //sentences.clear();
                sentences.addAll(grammarSentencesFromLiveData);
            }
        });
    }
    private String[] disassembleSentence(String sentence){
        String[] sent = sentence.split(" ");
        return sent;
    }

    private void addSentences(){
        sentences.add(new GrammarSentence("He writes", "Он пишет"));
        sentences.add(new GrammarSentence("We did not give", "Мы не дали"));
        sentences.add(new GrammarSentence("We won't show", "Мы не покажем"));
        sentences.add(new GrammarSentence("I won't work", "Я не буду работать"));
        sentences.add(new GrammarSentence("We will live in New York", "Мы будем жить в Нью-Йорке"));
        sentences.add(new GrammarSentence("We will not take", "Мы не возьмем"));
        sentences.add(new GrammarSentence("They worked", "Они работали"));
        sentences.add(new GrammarSentence("They didn't take", "Они не брали"));
        sentences.add(new GrammarSentence("He doesn't eat", "Он не ест"));
        sentences.add(new GrammarSentence("We will look", "Мы посмотрим"));
        sentences.add(new GrammarSentence("I will read", "Я буду читать"));
        sentences.add(new GrammarSentence("Did they tell ?", "Они рассказали ?"));
        sentences.add(new GrammarSentence("I didn't meet", "Я не встретил"));
        sentences.add(new GrammarSentence("You left", "Ты оставил"));
        sentences.add(new GrammarSentence("Will he speak ?", "Он будет говорить ?"));
    }
    private void addWrongAnswers(){
        wrongAnswers.add("I");
        wrongAnswers.add("We");
        wrongAnswers.add("He");
        wrongAnswers.add("She");
        wrongAnswers.add("It");
        wrongAnswers.add("They");
        wrongAnswers.add("We");
        wrongAnswers.add("Work");
        wrongAnswers.add("lives");
        wrongAnswers.add("bought");
        wrongAnswers.add("show");
        wrongAnswers.add("take");
        wrongAnswers.add("I");
        wrongAnswers.add("leave");
        wrongAnswers.add("come");
        wrongAnswers.add("go");
        wrongAnswers.add("do");
    }
}
