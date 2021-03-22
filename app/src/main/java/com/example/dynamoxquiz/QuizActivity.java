package com.example.dynamoxquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dynamoxquiz.controllers.QuizController;
import com.example.dynamoxquiz.controllers.UserController;
import com.example.dynamoxquiz.models.Quiz;
import com.example.dynamoxquiz.models.User;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "dynamox.EXTRA_USER_ID";

    private UserController userController;
    private QuizController quizController;

    private TextView userText;
    private TextView statementText;
    private Button button;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        userController = new UserController(this);

        userText = findViewById(R.id.userText);
        statementText = findViewById(R.id.statementText);
        button = findViewById(R.id.button);
        group = findViewById(R.id.radioGroup);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_USER_ID)) {
            userController.getUser(this, intent.getIntExtra(EXTRA_USER_ID, 0));
        } else {
            finish();
        }

    }

    public void setQuestionOptions(List<String> options) {
        group.removeAllViews();

        int index = 0;

        for (String option: options) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setId(++index);
            group.addView(radioButton);
        }
    }

    public void setStatementText(String text) {
        statementText.setText(text);
    }

    public void setUserText(String text) {
        userText.setText(String.format("Olá, %s", text));
    }

    public void setQuestionText(String text) {
        setTitle(String.format("Pergunta %s", text));
    }

    public void onClick(View v) {
        int id = group.getCheckedRadioButtonId();

        if (id != -1) {
            String value = ((RadioButton) findViewById(id)).getText().toString();
            quizController.checkAnswer(value);
        } else {
            Toast.makeText(this, getString(R.string.error_missing_answer), Toast.LENGTH_LONG).show();
        }
    }

    public void setUser(User user) {
        quizController = new QuizController(this, user);

        setUserText(user.nickname);
    }

    public void setQuiz(Quiz quiz) {
        quizController.setQuiz(quiz);
    }

    public void showNextQuestionDialog(boolean isRight) {
        int title = isRight ? R.string.right_answer : R.string.wrong_answer;
        int message = isRight ? R.string.right_answer_msg : R.string.wrong_answer_msg;

        new AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.next_question, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    quizController.nextQuestion(isRight);
                }
            })
            .show();
    }
}
