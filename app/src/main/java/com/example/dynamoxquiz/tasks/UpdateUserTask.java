package com.example.dynamoxquiz.tasks;

import android.os.AsyncTask;

import com.example.dynamoxquiz.QuizActivity;
import com.example.dynamoxquiz.dao.AppDatabase;
import com.example.dynamoxquiz.dao.DatabaseModule;
import com.example.dynamoxquiz.models.User;

import java.lang.ref.WeakReference;

public class UpdateUserTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<QuizActivity> weakActivity;
    private AppDatabase db;
    private User user;

    public UpdateUserTask(QuizActivity activity, User user) {
        this.weakActivity = new WeakReference<QuizActivity>(activity);
        this.db = DatabaseModule.getInstance(weakActivity.get());
        this.user = user;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            db.userDao().update(user);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        QuizActivity activity = weakActivity.get();

        activity.finish();
    }
}
