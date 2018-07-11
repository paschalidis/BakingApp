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

/**
 * Singleton Class
 */
public class RecipeRepository {
    private ApiInterface mApiClient;
    private MutableLiveData<List<Recipe>> mRecipeResponce;

    private static RecipeRepository mInstance;

    public RecipeRepository() {
        mApiClient = ApiClient.getClient().create(ApiInterface.class);
        mRecipeResponce = new MutableLiveData<>();
    }

    public static RecipeRepository getInstance() {
        if (mInstance == null) {
            mInstance = new RecipeRepository();
        }
        return mInstance;
    }

    public LiveData<List<Recipe>> getRecipes() {

        if (mRecipeResponce.getValue() != null) {
            return mRecipeResponce;
        }

        mApiClient.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                mRecipeResponce.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });

        return mRecipeResponce;
    }
}
