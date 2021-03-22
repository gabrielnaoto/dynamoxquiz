package com.example.dynamoxquiz.controllers;

import android.app.Activity;
import android.widget.Toast;

import com.example.dynamoxquiz.QuizActivity;
import com.example.dynamoxquiz.R;
import com.example.dynamoxquiz.models.Quiz;
import com.example.dynamoxquiz.models.User;
import com.example.dynamoxquiz.services.ApiService;
import com.example.dynamoxquiz.services.RetrofitFactory;
import com.example.dynamoxquiz.services.models.Question;
import com.example.dynamoxquiz.tasks.CreateNewQuizTask;
import com.example.dynamoxquiz.tasks.InsertUserTask;
import com.example.dynamoxquiz.tasks.LoadOngoingQuizTask;
import com.example.dynamoxquiz.tasks.UpdateQuizTask;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizController {

    private User user;
    private Quiz quiz;
    private QuizActivity activity;
    private ApiService service;
    private Question question;

    public QuizController(QuizActivity activity, User user) {
        this.user = user;
        this.activity = activity;
        this.service = RetrofitFactory.getInstance().getService();

        new LoadOngoingQuizTask(activity, user.uid).execute();
    }

    public void loadQuestion () {
        Call<Question> call = service.getQuestion();

        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if (response.isSuccessful()) {
                    setQuestion(response.body());
                }

            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void setQuestion(Question question) {
        this.question = question;

        activity.setStatementText(question.getStatement());
        activity.setQuestionOptions(question.getOptions());
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

        loadQuestion();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
