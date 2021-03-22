package com.example.dynamoxquiz.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = {"nickname"}, unique = true)})
public class User {
    public User(String nickname) {
        this.nickname = nickname;
        this.active = true;
    }

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "nickname")
    public String nickname;

    @ColumnInfo(name = "active", defaultValue = "true")
    public boolean active;
}
