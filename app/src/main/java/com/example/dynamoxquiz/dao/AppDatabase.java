package com.example.dynamoxquiz.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dynamoxquiz.models.User;

@Database(entities = {User.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
