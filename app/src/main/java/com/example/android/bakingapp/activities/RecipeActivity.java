package com.example.android.bakingapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.fragments.RecipeDetailFragment;
import com.example.android.bakingapp.fragments.RecipeFragment;
import com.example.android.bakingapp.interfaces.StepOnClickHandler;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.Step;

public class RecipeActivity extends AppCompatActivity implements StepOnClickHandler {

    public static final String STEP_ENTITY = "step_entity";
    private Recipe mRecipe;
    private boolean mTwoPane;

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

        if (findViewById(R.id.recipe_activity_linear_layout) != null) {
            mTwoPane = true;

            // Only create new fragments when there is no previously saved state
//            if (savedInstanceState == null) {
//                RecipeFragment recipeFragment = new RecipeFragment();
//                recipeFragment.setRecipe(mRecipe);
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .add(R.id.recipe_fragment_container, recipeFragment)
//                        .commit();

//                RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
//                recipeDetailFragment.setStep(mStep);
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .add(R.id.recipe_detail_fragment_container, recipeDetailFragment)
//                        .commit();
//            }

        } else {
            mTwoPane = false;
        }

        if (savedInstanceState == null) {
            RecipeFragment recipeFragment = new RecipeFragment();
            recipeFragment.setRecipe(mRecipe);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_fragment_container, recipeFragment)
                    .commit();
        }

    }

    @Override
    public void onStepClick(Step step) {

        if (mTwoPane) {
            RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
            recipeDetailFragment.setStep(step);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.recipe_detail_fragment_container, recipeDetailFragment)
                    .commit();
        } else {
            Intent intentToStartRecipeDetailActivity = new Intent(this, RecipeDetailActivity.class);
            intentToStartRecipeDetailActivity.putExtra(STEP_ENTITY, step);
            startActivity(intentToStartRecipeDetailActivity);
        }
    }
}
