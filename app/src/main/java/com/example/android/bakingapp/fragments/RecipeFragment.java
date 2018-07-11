package com.example.android.bakingapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.RecipeDetailActivity;
import com.example.android.bakingapp.adapters.StepAdapter;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.Step;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment implements StepAdapter.StepAdapterOnClickHandler {

    public static final String RECIPE_OBJECT = "recipe_object";
    public static final String STEP_ENTITY = "step_entity";

    // Tag for logging
    private static final String TAG = RecipeFragment.class.getSimpleName();

    private Recipe mRecipe;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mRecipe = savedInstanceState.getParcelable(RECIPE_OBJECT);
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);

        if (mRecipe != null) {
            TextView textView = rootView.findViewById(R.id.recipe_ingredients_text_view);
            textView.setText(mRecipe.getName());

            RecyclerView recyclerView = rootView.findViewById(R.id.recipe_steps_recycler_view);

            LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);

            StepAdapter stepAdapter = new StepAdapter(rootView.getContext(), this);

            stepAdapter.setStepData(mRecipe.getSteps());
            recyclerView.setAdapter(stepAdapter);
        } else {
            Log.v(TAG, "This fragment has a null list of image id's");
        }


        return rootView;
    }

    public void setRecipe(Recipe recipe) {
        mRecipe = recipe;
    }

    @Override
    public void onClick(Step step) {
        Intent intentToStartRecipeDetailActivity = new Intent(getContext(), RecipeDetailActivity.class);
        intentToStartRecipeDetailActivity.putExtra(STEP_ENTITY, step);
        startActivity(intentToStartRecipeDetailActivity);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(RECIPE_OBJECT, mRecipe);
    }
}
