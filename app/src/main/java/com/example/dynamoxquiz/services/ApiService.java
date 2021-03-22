package com.example.dynamoxquiz.services;

import com.example.dynamoxquiz.services.models.Answer;
import com.example.dynamoxquiz.services.models.Question;
import com.example.dynamoxquiz.services.models.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/question")
    Call<Question> getQuestion();

    @POST("/answer?questionId={id}")
    Call<Response> checkAnswer(@Path("id") int id, @Body Answer answer);
}
