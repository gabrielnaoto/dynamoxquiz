package com.example.dynamoxquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.dynamoxquiz.controllers.UserController;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private UserController controller;
    private TextInputEditText nicknameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nicknameInput = findViewById(R.id.nicknameInput);
        controller = new UserController(this);
        controller.loadActiveUser();
    }

    public void onClick(View v) {
        String nickname = nicknameInput.getText().toString().trim().toLowerCase();

        controller.saveUser(nickname);

        nicknameInput.setText(null);
    }
}