package com.example.android.bakingapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.fragments.RecipeStepFragment;
import com.example.android.bakingapp.fragments.RecipeFragment;
import com.example.android.bakingapp.clickHandlers.IngredientOnClickHandler;
import com.example.android.bakingapp.clickHandlers.StepOnClickHandler;
import com.example.android.bakingapp.fragments.RecipeIngredientFragment;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.Step;

public class RecipeActivity extends AppCompatActivity implements StepOnClickHandler, IngredientOnClickHandler {

    public static final String INGREDIENT_ENTITY = "ingredient_entity";
    public static final String STEP_INDEX = "step_index";
    public static final String STEP_LIST = "step_list";

    private Recipe mRecipe;
    private static boolean mTwoPane;

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
            if (savedInstanceState == null) {
                // Set by default ingredient list on tablet
                RecipeIngredientFragment recipeIngredientFragment = new RecipeIngredientFragment();
                recipeIngredientFragment.setIngredients(mRecipe.getIngredients());
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.recipe_detail_fragment_container, recipeIngredientFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }

        // Only create new fragments when there is no previously saved state
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
    public void onStepClick(Step step, int position) {
        if (mTwoPane) {
            RecipeStepFragment recipeStepFragment = new RecipeStepFragment();
            recipeStepFragment.setStep(step);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.recipe_detail_fragment_container, recipeStepFragment)
                    .commit();
        } else {
            Intent intentToStartRecipeDetailActivity = new Intent(this, RecipeDetailActivity.class);
            intentToStartRecipeDetailActivity.putExtra(STEP_INDEX, position);
            intentToStartRecipeDetailActivity.putParcelableArrayListExtra(STEP_LIST, mRecipe.getSteps());
            startActivity(intentToStartRecipeDetailActivity);
        }
    }

    @Override
    public void onIngredientClick(View view) {
        if (mTwoPane) {
            RecipeIngredientFragment recipeIngredientFragment = new RecipeIngredientFragment();
            recipeIngredientFragment.setIngredients(mRecipe.getIngredients());
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.recipe_detail_fragment_container, recipeIngredientFragment)
                    .commit();
        } else {
            Intent intentToStartRecipeDetailActivity = new Intent(view.getContext(), RecipeDetailActivity.class);
            intentToStartRecipeDetailActivity.putParcelableArrayListExtra(INGREDIENT_ENTITY, mRecipe.getIngredients());
            startActivity(intentToStartRecipeDetailActivity);
        }
    }

    public static boolean getTwoPane() {
        return mTwoPane;
    }
}
