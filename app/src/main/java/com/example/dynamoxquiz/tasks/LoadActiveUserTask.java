package com.example.dynamoxquiz.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.dynamoxquiz.QuizActivity;
import com.example.dynamoxquiz.dao.AppDatabase;
import com.example.dynamoxquiz.models.User;

import java.lang.ref.WeakReference;

public class LoadActiveUserTask extends AsyncTask<Void, Void, User> {

    private WeakReference<Activity> weakActivity;
    private AppDatabase db;

    public LoadActiveUserTask(Activity activity) {
        this.weakActivity = new WeakReference<>(activity);
        this.db = AppDatabase.getInstance(weakActivity.get());
    }

    @Override
    protected User doInBackground(Void... voids) {
        try {
            return db.userDao().getActiveUser();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(User user) {
        Activity activity = weakActivity.get();

        if (activity == null) {
            return;
        }

        if (user != null) {
            Intent intent = new Intent(activity, QuizActivity.class);
            intent.putExtra(QuizActivity.EXTRA_USER_ID, user.uid);
            activity.startActivity(intent);
        }
    }
}
