package com.example.android.bakingapp.activities;

import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.fragments.RecipeDetailFragment;
import com.example.android.bakingapp.models.Step;

public class RecipeDetailActivity extends AppCompatActivity {

    private Step mStep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(RecipeActivity.STEP_ENTITY)) {
                mStep = intentThatStartedThisActivity.getParcelableExtra(RecipeActivity.STEP_ENTITY);
            }
        }

        // Only create new fragments when there is no previously saved state
        if (savedInstanceState == null) {
            RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
            recipeDetailFragment.setStep(mStep);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_detail_fragment_container, recipeDetailFragment)
                    .commit();
        }
    }
}
