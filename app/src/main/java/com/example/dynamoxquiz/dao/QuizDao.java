package com.example.dynamoxquiz.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dynamoxquiz.models.Quiz;

@Dao
public interface QuizDao {
    @Query("SELECT * FROM quizzes WHERE user_id = :userId AND current_question < 10 ORDER BY uid DESC LIMIT 1")
    Quiz getOngoingQuiz(int userId);

    @Query("SELECT * FROM quizzes WHERE uid = :id")
    Quiz getQuizById(int id);

    @Insert
    long insert(Quiz quiz);

    @Update
    void update(Quiz quiz);

    @Delete
    void delete(Quiz quiz);
}
