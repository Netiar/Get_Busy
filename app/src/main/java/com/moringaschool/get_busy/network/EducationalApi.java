package com.moringaschool.get_busy.network;

import com.moringaschool.get_busy.models.ResultOpenDb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EducationalApi {
    @GET("api.php")
    Call<ResultOpenDb> getAllItems(
            @Query("amount") Integer searchAmount,
            @Query("category") Integer searchCategory,
            @Query("difficulty") String searchDifficulty,
            @Query("type") String searchType
    );


}
