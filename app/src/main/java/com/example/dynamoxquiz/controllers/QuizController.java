package com.example.dynamoxquiz.controllers;

import android.app.Activity;

import com.example.dynamoxquiz.QuizActivity;
import com.example.dynamoxquiz.models.Quiz;
import com.example.dynamoxquiz.models.User;
import com.example.dynamoxquiz.tasks.CreateNewQuizTask;
import com.example.dynamoxquiz.tasks.InsertUserTask;
import com.example.dynamoxquiz.tasks.LoadOngoingQuizTask;
import com.example.dynamoxquiz.tasks.UpdateQuizTask;

public class QuizController {

    private User user;
    private Quiz quiz;
    private QuizActivity activity;

    public QuizController(QuizActivity activity, User user) {
        this.user = user;
        this.activity = activity;

        new LoadOngoingQuizTask(activity, user.uid).execute();
    }

    public void nextQuestion () {
        quiz.currentQuestion += 1;

        new UpdateQuizTask(activity, quiz).execute();
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;

        activity.setQuestionText(Integer.toString(quiz.currentQuestion));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
