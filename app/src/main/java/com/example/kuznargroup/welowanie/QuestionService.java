package com.example.kuznargroup.welowanie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by makal on 01.05.2016.
 */


public interface QuestionService {
    //@GET("question.php{id}")
    //Call<List<QuestionsAnswers>> getQuestionsAnswers(@Query("id") int position);

    String base = "http://46.101.128.24/";
    @GET("question.php?id=1")
    Call<Pytania> getPytania();


    class Factory{
        public static QuestionService service;
        public static QuestionService getIstance(){
            if(service == null){
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(base).build();
                service = retrofit.create(QuestionService.class);
                return service;
            }else{
                return service;
            }

        }
    }

}
