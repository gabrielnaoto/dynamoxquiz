package com.example.dynamoxquiz.controllers;

import android.app.Activity;

import com.example.dynamoxquiz.models.User;
import com.example.dynamoxquiz.tasks.InsertUserTask;
import com.example.dynamoxquiz.tasks.LoadActiveUserTask;

public class UserController {

    public UserController() {
    }

    public void saveUser(Activity activity, String nickname) {
        User user = new User(nickname);

        new InsertUserTask(activity, user).execute();
    }

    public void loadActiveUser(Activity activity) {
        new LoadActiveUserTask(activity).execute();
    }
}
