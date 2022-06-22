package com.moringaschool.get_busy.network;

import static com.moringaschool.get_busy.constants.Constants.EDUCATIONAL_BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EducationalApiClient {
    private static Retrofit retrofit = null;

    public static EducationalApi getClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(EDUCATIONAL_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(EducationalApi.class);
    }
}
