package com.example.android.bakingapp.fragments;

import android.content.Context;
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
import com.example.android.bakingapp.adapters.StepAdapter;
import com.example.android.bakingapp.clickHandlers.IngredientOnClickHandler;
import com.example.android.bakingapp.clickHandlers.StepOnClickHandler;
import com.example.android.bakingapp.models.Recipe;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment {

    public static final String RECIPE_OBJECT = "recipe_object";

    // Tag for logging
    private static final String TAG = RecipeFragment.class.getSimpleName();

    private Recipe mRecipe;
    private StepOnClickHandler mStepOnClickHandler;
    private IngredientOnClickHandler mIngredientOnClickHandler;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mRecipe = savedInstanceState.getParcelable(RECIPE_OBJECT);
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);

        if (mRecipe != null) {

            TextView textView = rootView.findViewById(R.id.recipe_ingredients_text_view);

            RecyclerView recyclerView = rootView.findViewById(R.id.recipe_steps_recycler_view);

            LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);

            StepAdapter stepAdapter = new StepAdapter(getContext(), mStepOnClickHandler, mRecipe.getSteps());

            recyclerView.setAdapter(stepAdapter);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIngredientOnClickHandler.onIngredientClick(v);
                }
            });
        } else {
            Log.v(TAG, "This fragment has a null list of recipes");
        }


        return rootView;
    }

    public void setRecipe(Recipe recipe) {
        mRecipe = recipe;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(RECIPE_OBJECT, mRecipe);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mStepOnClickHandler = (StepOnClickHandler) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement StepAdapterClickHandler");
        }

        try {
            mIngredientOnClickHandler = (IngredientOnClickHandler) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement IngredientOnClickHandler");
        }
    }
}
