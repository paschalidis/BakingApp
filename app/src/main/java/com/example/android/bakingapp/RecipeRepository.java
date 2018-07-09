package com.example.android.bakingapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.recipeApi.ApiClient;
import com.example.android.bakingapp.recipeApi.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {
    private ApiInterface mApiClient;

    public RecipeRepository() {
        mApiClient = ApiClient.getClient().create(ApiInterface.class);
    }

    public LiveData<List<Recipe>> getRecipes() {

        final MutableLiveData<List<Recipe>> data = new MutableLiveData<>();

        mApiClient.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });

        return data;
    }
}
