package com.valera.diplom.Lessons;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.valera.diplom.R;

import java.util.Timer;
import java.util.TimerTask;

public class ListeningActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private ImageView imageViewPlay;
    private SeekBar seekBar;
    private AudioManager audioManager;
    private Spinner spinnerFirst;
    private Spinner spinnerSecond;
    private Spinner spinnerThird;
    private Spinner spinnerFourth;
    private Spinner spinnerFive;
    private TextView textViewAnswer;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        spinnerFirst = findViewById(R.id.spinnerFirst);
        spinnerSecond = findViewById(R.id.spinnerSecond);
        spinnerThird = findViewById(R.id.spinnerThird);
        spinnerFourth = findViewById(R.id.spinnerFourth);
        spinnerFive = findViewById(R.id.spinnerFive);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        mediaPlayer = MediaPlayer.create(this, R.raw.text_1);
        imageViewPlay = findViewById(R.id.imageViewPlay);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    pause();
                }
                else{
                    play();
                }
            }
        });
    }

    public void play() {
        mediaPlayer.start();
        imageViewPlay.setImageResource(android.R.drawable.ic_media_pause);
    }

    public void pause() {
        mediaPlayer.pause();
        imageViewPlay.setImageResource(android.R.drawable.ic_media_play);
    }
//#FF0000
    //#41DA48
    public void onClickCheckTask(View view) {
        int colorRed = Color.parseColor("#FF0000");
        int colorGreen = Color.parseColor("#41DA48");
        String rightAnswerFirst = "bad";
        String rightAnswerSecond = "fine";
        String rightAnswerThird = "She has much homework and headache";
        String rightAnswerFourth = "yes";
        String rightAnswerFive = "two";
        if(count > 2){
            textViewAnswer.setVisibility(View.VISIBLE);
        }
        if(spinnerFirst.getSelectedItem().toString().equals(rightAnswerFirst)){
            spinnerFirst.setBackgroundColor(colorGreen);
        }
        else{
            spinnerFirst.setBackgroundColor(colorRed);
        }
        if(spinnerSecond.getSelectedItem().toString().equals(rightAnswerSecond)){
            spinnerSecond.setBackgroundColor(colorGreen);
        }
        else{
            spinnerSecond.setBackgroundColor(colorRed);
        }
        if(spinnerThird.getSelectedItem().toString().equals(rightAnswerThird)){
            spinnerThird.setBackgroundColor(colorGreen);
        }
        else{
            spinnerThird.setBackgroundColor(colorRed);
        }
        if(spinnerFourth.getSelectedItem().toString().equals(rightAnswerFourth)){
            spinnerFourth.setBackgroundColor(colorGreen);
        }
        else{
            spinnerFourth.setBackgroundColor(colorRed);
        }
        if(spinnerFive.getSelectedItem().toString().equals(rightAnswerFive)){
            spinnerFive.setBackgroundColor(colorGreen);
        }
        else{
            spinnerFive.setBackgroundColor(colorRed);
        }
        count++;
    }
}
