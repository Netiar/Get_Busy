package com.moringaschool.get_busy.network;

import static com.moringaschool.get_busy.constants.Constants.BORED_BASE_URL;

import com.google.android.gms.common.api.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.moringaschool.get_busy.constants.Constants.*;

public class BoredApiClient {

    private static Retrofit retrofit = null;

    public static BoredApi getClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BORED_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(BoredApi.class);
    }

}
