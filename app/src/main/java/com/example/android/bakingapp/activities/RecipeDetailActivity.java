package com.example.android.bakingapp.activities;

import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.clickHandlers.StepNavigationOnClickHandler;
import com.example.android.bakingapp.fragments.RecipeDetailFragment;
import com.example.android.bakingapp.models.Ingredient;
import com.example.android.bakingapp.models.Step;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity implements StepNavigationOnClickHandler {

    private static String CURRENT_STEP_INDEX = "current_step_index";
    private static String STEP_LIST = "step_list";

    private ArrayList<Step> mStepList;
    private ArrayList<Ingredient> mIngredients;
    private int mCurrentStepIndex;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_STEP_INDEX, mCurrentStepIndex);
        outState.putParcelableArrayList(STEP_LIST, mStepList);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(RecipeActivity.STEP_LIST)) {
                mStepList = intentThatStartedThisActivity.getParcelableArrayListExtra(RecipeActivity.STEP_LIST);
            }
            if (intentThatStartedThisActivity.hasExtra(RecipeActivity.INGREDIENT_ENTITY)) {
                mIngredients = intentThatStartedThisActivity.getParcelableArrayListExtra(RecipeActivity.INGREDIENT_ENTITY);
            }
            if (intentThatStartedThisActivity.hasExtra(RecipeActivity.STEP_INDEX)) {
                mCurrentStepIndex = intentThatStartedThisActivity.getIntExtra(RecipeActivity.STEP_INDEX, 0);
            }
        }

        // Only create new fragments when there is no previously saved state
        if (savedInstanceState == null) {
            RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
            if (mStepList != null) {
                recipeDetailFragment.setStep(mStepList.get(mCurrentStepIndex));
                recipeDetailFragment.setLastStepIndex(mStepList.size());
                recipeDetailFragment.setStepIndex(mCurrentStepIndex);
            }

            if (mIngredients != null) {
                recipeDetailFragment.setIngredients(mIngredients);
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_detail_fragment_container, recipeDetailFragment)
                    .commit();
        } else {
            mCurrentStepIndex = savedInstanceState.getInt(CURRENT_STEP_INDEX);
            mStepList = savedInstanceState.getParcelableArrayList(STEP_LIST);
        }
    }

    @Override
    public void onNextClick(View view) {
        mCurrentStepIndex++;
        Step nextStep = mStepList.get(mCurrentStepIndex);
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setStep(nextStep);
        recipeDetailFragment.setLastStepIndex(mStepList.size());
        recipeDetailFragment.setStepIndex(mCurrentStepIndex);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.recipe_detail_fragment_container, recipeDetailFragment)
                .commit();
    }

    @Override
    public void onPreciousClick(View view) {
        mCurrentStepIndex--;
        Step previousStep = mStepList.get(mCurrentStepIndex);
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setStep(previousStep);
        recipeDetailFragment.setLastStepIndex(mStepList.size());
        recipeDetailFragment.setStepIndex(mCurrentStepIndex);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.recipe_detail_fragment_container, recipeDetailFragment)
                .commit();
    }
}
