package com.moringaschool.get_busy.network;

import com.moringaschool.get_busy.models.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BoredApi {
    @GET("api/activity/")
    Call<Result> getRandomItem(
    );

}
