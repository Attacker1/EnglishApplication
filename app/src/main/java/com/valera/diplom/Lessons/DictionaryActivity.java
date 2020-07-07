package com.valera.diplom.Lessons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.valera.diplom.LessonsActivity;
import com.valera.diplom.R;
import com.valera.diplom.pojo.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DictionaryActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewOpinion0;
    private TextView textViewOpinion1;
    private TextView textViewOpinion2;
    private TextView textViewOpinion3;
    private ArrayList<TextView> options = new ArrayList<>();

    private String question;
    private String rightAnswer;
    private int rightAnswerPosition;
    private List<Word> dictionary;
    private Random rand;
    private int countOfQuestions = 0;
    private int countOfRightAnswers = 0;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewScore = findViewById(R.id.textViewScore);
        textViewOpinion0 = findViewById(R.id.textViewOpinion0);
        textViewOpinion1 = findViewById(R.id.textViewOpinion1);
        textViewOpinion2 = findViewById(R.id.textViewOpinion2);
        textViewOpinion3 = findViewById(R.id.textViewOpinion3);
        options.add(textViewOpinion0);
        options.add(textViewOpinion1);
        options.add(textViewOpinion2);
        options.add(textViewOpinion3);
        dictionary = new ArrayList<>();
        db = FirebaseFirestore.getInstance();


        db.collection("dictionary").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    Toast.makeText(DictionaryActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    QuerySnapshot querySnapshot = task.getResult();
                    if(querySnapshot == null) return;
                    dictionary = querySnapshot.toObjects(Word.class);
                    LessonsActivity.countOfBalls01 = dictionary.size();
                    playNext();
                }
                else{
                    Toast.makeText(DictionaryActivity.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void playNext(){
        generateQuestion();
        for(int i=0; i < options.size(); i++){
            if(i == rightAnswerPosition){
                options.get(i).setText(rightAnswer);
            }else{
                options.get(i).setText(generateWrongAnswer());
            }
        }
        String score = String.format("%s / %s", countOfRightAnswers, countOfQuestions);
        textViewScore.setText(score);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferences.edit().putString("lastScore", score).apply();
    }

    private void generateQuestion(){
        Word word;
        rand = new Random();
        word = dictionary.get(rand.nextInt(dictionary.size()));
        question = word.getTranslation();
        rightAnswer = word.getWord();
        textViewQuestion.setText(question);
        rightAnswerPosition = (int) (Math.random() * 4);
    }

    private String generateWrongAnswer(){
        String result;
        rand = new Random();
        do{
            result = dictionary.get(rand.nextInt(dictionary.size())).getWord();
        } while (result.equals(rightAnswer));
        return result;
    }


    public void onClickAnswer(View view) {
        TextView textView = (TextView) view;
        String chosenAnswer = textView.getText().toString();
        if(chosenAnswer.equals(rightAnswer)){
            countOfRightAnswers++;
            Toast.makeText(this, "Верно", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Неверно, правильный ответ: " + rightAnswer, Toast.LENGTH_SHORT).show();
        }
        countOfQuestions++;
        playNext();
    }
}
