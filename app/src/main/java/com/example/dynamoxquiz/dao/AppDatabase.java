package com.example.dynamoxquiz.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dynamoxquiz.models.Quiz;
import com.example.dynamoxquiz.models.User;

@Database(entities = {User.class, Quiz.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "dynamox";

    public abstract UserDao userDao();
    public abstract QuizDao quizDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room
                            .databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return instance;
    }
}
