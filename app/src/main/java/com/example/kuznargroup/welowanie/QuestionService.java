package com.example.kuznargroup.welowanie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by makal on 01.05.2016.
 */


public interface QuestionService {
    @GET("question.php?id=1")
    Call<pytania> getQuestionsAnswers();

}
