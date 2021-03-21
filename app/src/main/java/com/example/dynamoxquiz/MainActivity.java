package com.example.dynamoxquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.dynamoxquiz.controllers.UserController;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private UserController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new UserController();
    }

    public void onClick(View v) {
        TextInputEditText nicknameInput = findViewById(R.id.nicknameInput);
        String nickname = nicknameInput.getText().toString().trim();

        controller.startQuiz(this, nickname);
    }
}