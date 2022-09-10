package com.moringaschool.get_busy.network;

import com.moringaschool.get_busy.models.ResultOpenDb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EducationalApi {
    @GET("api.php")
    Call<ResultOpenDb> getAllItems(
            @Query("amount") Integer searchAmount,
            @Query("category") int searchCategory,
            @Query("difficulty") String searchDifficulty,
            @Query("type") String searchType
    );

    @GET("api.php")
    Call<ResultOpenDb> getAllItems1(
            @Query("amount") Integer searchAmount,
            @Query("type") String searchType,
            @Query("difficulty") String searchDifficulty
    );


}
