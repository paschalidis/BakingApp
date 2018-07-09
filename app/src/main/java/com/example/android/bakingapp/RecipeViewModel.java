package com.example.android.bakingapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.android.bakingapp.models.Recipe;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    private LiveData<List<Recipe>> mRecipes;
    private RecipeRepository mRecipeRepository;

    public RecipeViewModel(Application application) {
        super(application);
        mRecipeRepository = new RecipeRepository();
    }


    public LiveData<List<Recipe>> getRecipes() {
        if (mRecipes == null) {
            mRecipes = mRecipeRepository.getRecipes();
        }
        return mRecipes;
    }

}
