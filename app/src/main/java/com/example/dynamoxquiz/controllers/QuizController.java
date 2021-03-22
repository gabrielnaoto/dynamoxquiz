package com.example.dynamoxquiz.controllers;

import android.app.Activity;

import com.example.dynamoxquiz.models.Quiz;
import com.example.dynamoxquiz.models.User;
import com.example.dynamoxquiz.tasks.InsertUserTask;

public class QuizController {

    private User user;
    private Quiz quiz;
    private Activity activity;

    public QuizController(Activity activity, User user) {
        this.user = user;
        this.activity = activity;
    }

    public void start() {

    }

    public void next() {

    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
