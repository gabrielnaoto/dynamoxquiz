package com.example.dynamoxquiz.tasks;

import android.os.AsyncTask;

import com.example.dynamoxquiz.QuizActivity;
import com.example.dynamoxquiz.dao.AppDatabase;
import com.example.dynamoxquiz.models.Quiz;

import java.lang.ref.WeakReference;

public class UpdateQuizTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<QuizActivity> weakActivity;
    private AppDatabase db;
    private Quiz quiz;

    public UpdateQuizTask(QuizActivity activity, Quiz quiz) {
        this.weakActivity = new WeakReference<QuizActivity>(activity);
        this.db = AppDatabase.getInstance(weakActivity.get());
        this.quiz = quiz;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            db.quizDao().update(quiz);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        QuizActivity activity = weakActivity.get();

        activity.setQuestionText(Integer.toString(quiz.currentQuestion));
    }
}
