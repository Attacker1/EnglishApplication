package com.valera.diplom.pojo;

public class Word {
    private String word;
    private String translation;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Word(String word, String translation, String type) {
        this.word = word;
        this.translation = translation;
        this.type = type;
    }

    public Word() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
