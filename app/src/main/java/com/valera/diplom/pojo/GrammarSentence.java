package com.valera.diplom.pojo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "sentences")
public class GrammarSentence {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String sentence;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String translation;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    @Ignore
    public GrammarSentence(String sentence, String translation) {
        this.sentence = sentence;
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public GrammarSentence() {
    }

    public GrammarSentence(int id, String sentence, String translation) {
        this.sentence = sentence;
        this.translation = translation;
        this.id = id;
    }
}
