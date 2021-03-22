package com.example.dynamoxquiz.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dynamoxquiz.models.Quiz;
import com.example.dynamoxquiz.models.User;

@Database(entities = {User.class, Quiz.class}, version = 9)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract QuizDao quizDao();
}
