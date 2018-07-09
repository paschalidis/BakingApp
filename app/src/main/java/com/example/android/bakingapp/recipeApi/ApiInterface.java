package com.example.android.bakingapp.recipeApi;

import com.example.android.bakingapp.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET(ApiUtilities.API_RECIPES_ENDPOINT)
    Call<List<Recipe>> getRecipes();
}
