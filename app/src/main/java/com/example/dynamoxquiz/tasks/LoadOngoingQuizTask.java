package com.example.dynamoxquiz.tasks;

import android.os.AsyncTask;

import com.example.dynamoxquiz.QuizActivity;
import com.example.dynamoxquiz.dao.AppDatabase;
import com.example.dynamoxquiz.models.Quiz;

import java.lang.ref.WeakReference;

public class LoadOngoingQuizTask extends AsyncTask<Void, Void, Quiz> {

    private WeakReference<QuizActivity> weakActivity;
    private AppDatabase db;
    private Integer userId;

    public LoadOngoingQuizTask(QuizActivity activity, Integer userId) {
        this.weakActivity = new WeakReference<QuizActivity>(activity);
        this.db = AppDatabase.getInstance(weakActivity.get());
        this.userId = userId;
    }

    @Override
    protected Quiz doInBackground(Void... voids) {
        try {
            return db.quizDao().getOngoingQuiz(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Quiz quiz) {
        QuizActivity activity = weakActivity.get();

        if (activity == null) {
            return;
        }

        if (quiz != null) {
            activity.setQuiz(quiz);
        } else {
            new CreateNewQuizTask(activity, userId).execute();
        }
    }
}
