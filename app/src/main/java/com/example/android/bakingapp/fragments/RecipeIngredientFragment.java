package com.example.android.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.IngredientAdapter;
import com.example.android.bakingapp.models.Ingredient;

import java.util.ArrayList;

public class RecipeIngredientFragment extends Fragment {

    public static final String INGREDIENT_LIST = "ingredient_list";

    private ArrayList<Ingredient> mIngredients;

    // Tag for logging
    private static final String TAG = RecipeIngredientFragment.class.getSimpleName();

    public RecipeIngredientFragment() {
        mIngredients = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mIngredients = savedInstanceState.getParcelableArrayList(INGREDIENT_LIST);
        }

        View rootView = inflater.inflate(R.layout.fragment_recipe_ingredient, container, false);

        if (mIngredients != null) {
            RecyclerView recyclerView = rootView.findViewById(R.id.recipe_ingredients_recycler_view);

            LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);

            IngredientAdapter ingredientAdapter = new IngredientAdapter(getContext(), mIngredients);

            recyclerView.setAdapter(ingredientAdapter);
        } else {
            Log.v(TAG, "This fragment has a null List of Ingredient");
        }

        return rootView;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        mIngredients = ingredients;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(INGREDIENT_LIST, mIngredients);
    }
}
