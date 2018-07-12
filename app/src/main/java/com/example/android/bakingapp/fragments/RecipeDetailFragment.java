package com.example.android.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Ingredient;
import com.example.android.bakingapp.models.Step;

import java.util.ArrayList;

public class RecipeDetailFragment extends Fragment {

    public static final String STEP_OBJECT = "step_object";
    public static final String INGREDIENT_LIST = "ingredient_list";

    // Tag for logging
    private static final String TAG = RecipeFragment.class.getSimpleName();

    private Step mStep;
    private ArrayList<Ingredient> mIngredients;

    public RecipeDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mStep = savedInstanceState.getParcelable(STEP_OBJECT);
            mIngredients = savedInstanceState.getParcelableArrayList(INGREDIENT_LIST);
        }

        View rootView = new View(inflater.getContext());

        if (mStep != null) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);

            TextView textView = rootView.findViewById(R.id.recipe_detail_description_text_view);
            textView.setText(mStep.getDescription());

        } else if (mIngredients != null) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_recipe_ingredient, container, false);
            TextView textView = rootView.findViewById(R.id.ingredient_text_view);
            textView.setText("Ingredient for fragment Wouaho!!!");
            return rootView;
        } else {
            Log.v(TAG, "This fragment has a null Step and null Ingredient");
        }

        return rootView;
    }

    public void setStep(Step step) {
        mStep = step;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        mIngredients = ingredients;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(STEP_OBJECT, mStep);
        outState.putParcelableArrayList(INGREDIENT_LIST, mIngredients);
    }
}
