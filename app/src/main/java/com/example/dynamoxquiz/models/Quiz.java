package com.example.dynamoxquiz.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "quizzes",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "uid",
                childColumns = "user_id",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = {"uid"})})
public class Quiz {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "current_question", defaultValue = "1")
    public int currentQuestion;

    @ColumnInfo(name = "score", defaultValue = "0")
    public int score;

    @ColumnInfo(name = "user_id")
    public int userId;
}
