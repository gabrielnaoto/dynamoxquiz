package com.example.dynamoxquiz.services;

import com.example.dynamoxquiz.services.models.Answer;
import com.example.dynamoxquiz.services.models.Question;
import com.example.dynamoxquiz.services.models.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/question")
    Call<Question> getQuestion();

    @POST("/answer?")
    Call<ResponseBody> checkAnswer(@Query("questionId") String id, @Body Answer answer);
}
