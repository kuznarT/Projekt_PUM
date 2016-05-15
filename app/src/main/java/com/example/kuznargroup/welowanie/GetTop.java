package com.example.kuznargroup.welowanie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kuznar on 2016-05-15.
 */
public interface GetTop {
    @GET("getscore.php")
    Call<TopScores> getScore();
}
