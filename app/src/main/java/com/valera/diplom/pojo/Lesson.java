package com.valera.diplom.pojo;

public class Lesson {
    private String title;
    private String description;
    private double countOfBalls;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCountOfBalls() {
        return countOfBalls;
    }

    public void setCountOfBalls(double countOfBalls) {
        this.countOfBalls = countOfBalls;
    }

    public Lesson(String title, String description, double countOfBalls) {
        this.title = title;
        this.description = description;
        this.countOfBalls = countOfBalls;
    }
    public Lesson(String title, double countOfBalls) {
        this.title = title;
        this.countOfBalls = countOfBalls;
    }
}
