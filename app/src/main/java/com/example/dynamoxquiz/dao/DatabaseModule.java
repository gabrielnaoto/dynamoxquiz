package com.example.dynamoxquiz.dao;

import android.content.Context;

import androidx.room.Room;

public class DatabaseModule {
    public static final String DATABASE_NAME = "dynamox";

    public static AppDatabase getInstance(Context context) {
        return Room
                .databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                // TODO remember to remove this after database is defined
                .fallbackToDestructiveMigration()
                .build();
    }
}
