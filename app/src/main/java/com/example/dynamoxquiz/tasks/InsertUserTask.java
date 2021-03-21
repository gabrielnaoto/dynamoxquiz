package com.example.dynamoxquiz.tasks;

import android.app.Activity;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.dynamoxquiz.dao.AppDatabase;
import com.example.dynamoxquiz.dao.DatabaseModule;
import com.example.dynamoxquiz.models.User;
import com.example.dynamoxquiz.R;

import java.lang.ref.WeakReference;

public class InsertUserTask extends AsyncTask<Void, Void, Integer> {

    private WeakReference<Activity> weakActivity;
    private AppDatabase db;
    private User user;

    public InsertUserTask(Activity activity, User user) {
        this.weakActivity = new WeakReference<>(activity);
        this.db = DatabaseModule.getInstance(weakActivity.get());
        this.user = user;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        try {
            db.userDao().insertAll(this.user);
            return 1;
        } catch (SQLiteConstraintException e) {
            return -1;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        Activity activity = weakActivity.get();
        if (activity == null) {
            return;
        }

        if (integer < 0) {
            Toast.makeText(activity, activity.getResources().getString(R.string.error_nickname_taken), Toast.LENGTH_LONG).show();
        }
    }
}
