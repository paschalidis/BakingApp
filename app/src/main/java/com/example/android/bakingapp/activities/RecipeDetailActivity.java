package com.example.android.bakingapp.activities;

import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.fragments.RecipeDetailFragment;
import com.example.android.bakingapp.models.Ingredient;
import com.example.android.bakingapp.models.Step;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    private Step mStep;
    private ArrayList<Ingredient> mIngredients;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(RecipeActivity.STEP_ENTITY)) {
                mStep = intentThatStartedThisActivity.getParcelableExtra(RecipeActivity.STEP_ENTITY);
            }
            if (intentThatStartedThisActivity.hasExtra(RecipeActivity.INGREDIENT_ENTITY)){
                mIngredients = intentThatStartedThisActivity.getParcelableArrayListExtra(RecipeActivity.INGREDIENT_ENTITY);
            }
        }

        // Only create new fragments when there is no previously saved state
        if (savedInstanceState == null) {
            RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
            if(mStep != null){
                recipeDetailFragment.setStep(mStep);
            }

            if(mIngredients != null){
                recipeDetailFragment.setIngredients(mIngredients);
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_detail_fragment_container, recipeDetailFragment)
                    .commit();
        }
    }
}
