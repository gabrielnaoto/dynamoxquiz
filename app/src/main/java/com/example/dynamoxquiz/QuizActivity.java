package com.example.dynamoxquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dynamoxquiz.controllers.QuizController;
import com.example.dynamoxquiz.controllers.UserController;
import com.example.dynamoxquiz.models.User;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "dynamox.EXTRA_USER_ID";

    private UserController userController;
    private QuizController quizController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        userController = new UserController(this);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_USER_ID)) {
            userController.getUser(this, intent.getIntExtra(EXTRA_USER_ID, 0));
        } else {
            finish();
        }

    }

    public void setUser(User user) {
        quizController = new QuizController(this, user);
    }
}