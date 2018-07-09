package com.example.android.bakingapp.recipeApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient() {
        if( mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiUtilities.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
