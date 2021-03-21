package com.example.dynamoxquiz.controllers;

import android.app.Activity;

import com.example.dynamoxquiz.models.User;
import com.example.dynamoxquiz.tasks.InsertUserTask;

public class UserController {

    public UserController() {
    }

    public void startQuiz(Activity activity, String nickname) {
        User user = new User(nickname);

        new InsertUserTask(activity, user).execute();
    }
}
