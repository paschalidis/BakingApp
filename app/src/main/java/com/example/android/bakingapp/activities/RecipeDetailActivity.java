package com.example.android.bakingapp.activities;

import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.widgets.RecipeWidgetService;
import com.example.android.bakingapp.clickHandlers.StepNavigationOnClickHandler;
import com.example.android.bakingapp.fragments.RecipeStepFragment;
import com.example.android.bakingapp.fragments.RecipeIngredientFragment;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.Step;

public class RecipeDetailActivity extends AppCompatActivity implements StepNavigationOnClickHandler {

    private static String CURRENT_STEP_INDEX = "current_step_index";
    private static String RECIPE_ENTITY = "recipe_entity";
    private static String SHOW_STEPS_FLAG = "show_steps";

    private int mCurrentStepIndex;
    private Recipe mRecipe;
    private Boolean mShowSteps;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_STEP_INDEX, mCurrentStepIndex);
        outState.putParcelable(RECIPE_ENTITY, mRecipe);
        outState.putBoolean(SHOW_STEPS_FLAG, mShowSteps);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(RecipeActivity.STEP_INDEX)) {
                mCurrentStepIndex = intentThatStartedThisActivity.getIntExtra(RecipeActivity.STEP_INDEX, 0);
                mShowSteps = true;
            } else {
                mShowSteps = false;
            }
            if (intentThatStartedThisActivity.hasExtra(RecipeActivity.RECIPE_ENTITY)) {
                mRecipe = intentThatStartedThisActivity.getParcelableExtra(RecipeActivity.RECIPE_ENTITY);
            }
        }

        // Only create new fragments when there is no previously saved state
        if (savedInstanceState == null) {
            if (mShowSteps) {
                RecipeStepFragment recipeStepFragment = new RecipeStepFragment();
                recipeStepFragment.setStep(mRecipe.getSteps().get(mCurrentStepIndex));
                recipeStepFragment.setLastStepIndex(mRecipe.getSteps().size());
                recipeStepFragment.setStepIndex(mCurrentStepIndex);

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.recipe_detail_fragment_container, recipeStepFragment)
                        .commit();
            } else {
                RecipeIngredientFragment recipeIngredientFragment = new RecipeIngredientFragment();
                recipeIngredientFragment.setIngredients(mRecipe.getIngredients());

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.recipe_detail_fragment_container, recipeIngredientFragment)
                        .commit();
            }
        } else {
            mCurrentStepIndex = savedInstanceState.getInt(CURRENT_STEP_INDEX);
            mShowSteps = savedInstanceState.getBoolean(SHOW_STEPS_FLAG);
            mRecipe = savedInstanceState.getParcelable(RECIPE_ENTITY);
        }

        if (mRecipe != null && !mRecipe.getName().isEmpty()) {
            setTitle(mRecipe.getName());
        }
    }

    @Override
    public void onNextClick(View view) {
        mCurrentStepIndex++;
        Step nextStep = mRecipe.getSteps().get(mCurrentStepIndex);
        RecipeStepFragment recipeStepFragment = new RecipeStepFragment();
        recipeStepFragment.setStep(nextStep);
        recipeStepFragment.setLastStepIndex(mRecipe.getSteps().size());
        recipeStepFragment.setStepIndex(mCurrentStepIndex);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.recipe_detail_fragment_container, recipeStepFragment)
                .commit();
    }

    @Override
    public void onPreviousClick(View view) {
        mCurrentStepIndex--;
        Step previousStep = mRecipe.getSteps().get(mCurrentStepIndex);
        RecipeStepFragment recipeStepFragment = new RecipeStepFragment();
        recipeStepFragment.setStep(previousStep);
        recipeStepFragment.setLastStepIndex(mRecipe.getSteps().size());
        recipeStepFragment.setStepIndex(mCurrentStepIndex);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.recipe_detail_fragment_container, recipeStepFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_to_widget_action) {
            RecipeWidgetService.startActionUpdateRecipeWidget(this, mRecipe);
        }
        return true;
    }
}
