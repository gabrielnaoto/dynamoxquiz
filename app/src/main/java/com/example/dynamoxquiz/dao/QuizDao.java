package com.example.dynamoxquiz.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dynamoxquiz.models.Quiz;
import com.example.dynamoxquiz.models.User;

@Dao
public interface QuizDao {
    @Query("SELECT * FROM quizzes WHERE user_id = :userId ORDER BY uid DESC LIMIT 1")
    Quiz getLast(int userId);

    @Query("SELECT * FROM quizzes WHERE uid = :id")
    Quiz getQuizById(int id);

    @Insert
    void insert(Quiz quiz);

    @Update
    void update(Quiz quiz);

    @Delete
    void delete(Quiz quiz);
}
