package com.example.dynamoxquiz.models;

public class User {

    private String name;
    private String nickname;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
