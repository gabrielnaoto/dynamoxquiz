package com.example.dynamoxquiz.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dynamoxquiz.models.Quiz;
import com.example.dynamoxquiz.models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE uid = :id")
    User getUserById(int id);

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE active = '1' ORDER BY uid LIMIT 1")
    User getActiveUser();

    @Insert
    long insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}