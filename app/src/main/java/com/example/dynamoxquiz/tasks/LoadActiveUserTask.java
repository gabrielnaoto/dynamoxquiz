package com.example.dynamoxquiz.tasks;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.dynamoxquiz.QuizActivity;
import com.example.dynamoxquiz.R;
import com.example.dynamoxquiz.dao.AppDatabase;
import com.example.dynamoxquiz.dao.DatabaseModule;
import com.example.dynamoxquiz.models.User;

import java.lang.ref.WeakReference;

public class LoadActiveUserTask extends AsyncTask<Void, Void, Integer> {

    private WeakReference<Activity> weakActivity;
    private AppDatabase db;
    private User user;

    public LoadActiveUserTask(Activity activity) {
        this.weakActivity = new WeakReference<>(activity);
        this.db = DatabaseModule.getInstance(weakActivity.get());
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        try {
            user = db.userDao().getActiveUser();
            return 1;
        } catch (SQLiteConstraintException e) {
            return -1;
        }
    }

    @Override
    protected void onPostExecute(Integer id) {
        Activity activity = weakActivity.get();

        if (activity == null) {
            return;
        }

        if (id > 0) {
            Intent intent = new Intent(activity, QuizActivity.class);
            intent.putExtra(QuizActivity.EXTRA_USER_ID, user.uid);
            activity.startActivity(intent);
        }
    }
}
