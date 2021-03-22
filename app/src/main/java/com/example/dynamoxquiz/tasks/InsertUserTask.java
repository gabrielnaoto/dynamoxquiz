package com.example.dynamoxquiz.tasks;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.dynamoxquiz.QuizActivity;
import com.example.dynamoxquiz.R;
import com.example.dynamoxquiz.dao.AppDatabase;
import com.example.dynamoxquiz.models.User;

import java.lang.ref.WeakReference;

public class InsertUserTask extends AsyncTask<Void, Void, Integer> {

    private WeakReference<Activity> weakActivity;
    private AppDatabase db;
    private User user;

    public InsertUserTask(Activity activity, User user) {
        this.weakActivity = new WeakReference<>(activity);
        this.db = AppDatabase.getInstance(weakActivity.get());
        this.user = user;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        try {
            return (int) (long) db.userDao().insert(this.user);
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

        if (id < 0) {
            Toast.makeText(activity, activity.getResources().getString(R.string.error_nickname_taken), Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(activity, QuizActivity.class);
            intent.putExtra(QuizActivity.EXTRA_USER_ID, id);
            activity.startActivity(intent);
        }
    }
}
