package com.example.dynamoxquiz.tasks;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.dynamoxquiz.QuizActivity;
import com.example.dynamoxquiz.R;
import com.example.dynamoxquiz.dao.AppDatabase;
import com.example.dynamoxquiz.dao.DatabaseModule;
import com.example.dynamoxquiz.models.Quiz;

import java.lang.ref.WeakReference;

public class CreateNewQuizTask extends AsyncTask<Void, Void, Quiz> {

    private WeakReference<QuizActivity> weakActivity;
    private AppDatabase db;
    private Quiz quiz;

    public CreateNewQuizTask(QuizActivity activity, Integer userId) {
        this.weakActivity = new WeakReference<QuizActivity>(activity);
        this.db = DatabaseModule.getInstance(weakActivity.get());
        this.quiz = new Quiz(userId);
    }

    @Override
    protected Quiz doInBackground(Void... voids) {
        try {
            long uid = db.quizDao().insert(quiz);
            quiz.uid = (int) uid;

            return quiz;
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
            Toast.makeText(activity, activity.getResources().getString(R.string.error_starting_quiz), Toast.LENGTH_LONG).show();
            activity.finish();
        }
    }
}
