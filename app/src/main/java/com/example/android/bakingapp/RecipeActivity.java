package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.example.android.bakingapp.fragments.RecipeFragment;
import com.example.android.bakingapp.models.Recipe;

public class RecipeActivity extends AppCompatActivity {

    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(MainActivity.RECIPE_ENTITY)) {
                mRecipe = intentThatStartedThisActivity.getParcelableExtra(MainActivity.RECIPE_ENTITY);
            }
        }

        if (savedInstanceState == null) {
            RecipeFragment recipeFragment = new RecipeFragment();
            recipeFragment.setRecipe(mRecipe);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_fragment, recipeFragment)
                    .commit();
        }
    }
}
