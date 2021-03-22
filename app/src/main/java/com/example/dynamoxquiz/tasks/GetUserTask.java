package com.example.dynamoxquiz.tasks;

import android.os.AsyncTask;

import com.example.dynamoxquiz.QuizActivity;
import com.example.dynamoxquiz.dao.AppDatabase;
import com.example.dynamoxquiz.models.User;

import java.lang.ref.WeakReference;

public class GetUserTask extends AsyncTask<Void, Void, User> {

    private WeakReference<QuizActivity> weakActivity;
    private AppDatabase db;
    private Integer uid;

    public GetUserTask(QuizActivity activity, Integer uid) {
        this.weakActivity = new WeakReference<QuizActivity>(activity);
        this.db = AppDatabase.getInstance(weakActivity.get());
        this.uid = uid;
    }

    @Override
    protected User doInBackground(Void... voids) {
        try {
            return db.userDao().getUserById(this.uid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(User user) {
        QuizActivity activity = weakActivity.get();

        if (activity == null) {
            return;
        }

        if (user != null) {
            activity.setUser(user);
        }
    }
}
