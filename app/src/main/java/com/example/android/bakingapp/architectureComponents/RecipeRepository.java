package com.example.android.bakingapp.architectureComponents;

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
    private MutableLiveData<List<Recipe>> mRecipeResponse;

    private static RecipeRepository mInstance;

    private RecipeRepository() {
        mApiClient = ApiClient.getClient().create(ApiInterface.class);
        mRecipeResponse = new MutableLiveData<>();
    }

    public static RecipeRepository getInstance() {
        if (mInstance == null) {
            mInstance = new RecipeRepository();
        }
        return mInstance;
    }

    public LiveData<List<Recipe>> getRecipes() {

        if (mRecipeResponse.getValue() != null) {
            return mRecipeResponse;
        }

        mApiClient.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                mRecipeResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });

        return mRecipeResponse;
    }
}
