package com.example.dynamoxquiz.controllers;

import android.app.Activity;

import com.example.dynamoxquiz.QuizActivity;
import com.example.dynamoxquiz.models.User;
import com.example.dynamoxquiz.tasks.GetUserTask;
import com.example.dynamoxquiz.tasks.InsertUserTask;
import com.example.dynamoxquiz.tasks.LoadActiveUserTask;

public class UserController {

    private Activity activity;

    public UserController(Activity activity) {
        this.activity = activity;
    }

    public void saveUser(String nickname) {
        User user = new User(nickname);

        new InsertUserTask(activity, user).execute();
    }

    public void loadActiveUser() {
        new LoadActiveUserTask(activity).execute();
    }

    public void getUser(QuizActivity activity, Integer uid) {
        new GetUserTask(activity, uid).execute();
    }
}
