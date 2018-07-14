package com.example.android.bakingapp.fragments;

import android.content.Context;
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
import android.widget.Button;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.RecipeActivity;
import com.example.android.bakingapp.adapters.IngredientAdapter;
import com.example.android.bakingapp.clickHandlers.StepNavigationOnClickHandler;
import com.example.android.bakingapp.models.Ingredient;
import com.example.android.bakingapp.models.Step;

import java.util.ArrayList;

public class RecipeDetailFragment extends Fragment {

    public static final String STEP_OBJECT = "step_object";
    public static final String INGREDIENT_LIST = "ingredient_list";
    public static final String LAST_STEP_INDEX = "last_step_index";
    public static final String STEP_INDEX = "step_index";

    // Tag for logging
    private static final String TAG = RecipeFragment.class.getSimpleName();

    private Step mStep;
    private int mLastStepIndex;
    private int mStepIndex;
    private ArrayList<Ingredient> mIngredients;
    private boolean mTwoPane;
    private StepNavigationOnClickHandler mStepNavigationOnClickHandler;

    public RecipeDetailFragment() {
        mTwoPane = false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mStep = savedInstanceState.getParcelable(STEP_OBJECT);
            mIngredients = savedInstanceState.getParcelableArrayList(INGREDIENT_LIST);
            mLastStepIndex = savedInstanceState.getInt(LAST_STEP_INDEX);
            mStepIndex = savedInstanceState.getInt(STEP_INDEX);
        }

        View rootView = new View(inflater.getContext());

        if (mStep != null) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);

            TextView textView = rootView.findViewById(R.id.recipe_detail_description_text_view);
            textView.setText(mStep.getDescription());

            Button nextButton = rootView.findViewById(R.id.next_step_button);
            Button previousButton = rootView.findViewById(R.id.previous_step_button);
            if (mTwoPane) {
                nextButton.setVisibility(View.GONE);
                previousButton.setVisibility(View.GONE);
            } else {
                if (mStepIndex == mLastStepIndex) {
                    nextButton.setVisibility(View.GONE);
                }
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mStepNavigationOnClickHandler.onNextClick(v);
                    }
                });

                if (mStepIndex == 0) {
                    previousButton.setVisibility(View.GONE);
                }
                previousButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mStepNavigationOnClickHandler.onPreciousClick(v);
                    }
                });
            }

        } else if (mIngredients != null) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_recipe_ingredient, container, false);

            RecyclerView recyclerView = rootView.findViewById(R.id.recipe_ingredients_recycler_view);

            LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);

            IngredientAdapter ingredientAdapter = new IngredientAdapter(getContext(), mIngredients);

            recyclerView.setAdapter(ingredientAdapter);

            return rootView;
        } else {
            Log.v(TAG, "This fragment has a null Step and null Ingredient");
        }

        return rootView;
    }

    public void setStep(Step step) {
        mStep = step;
    }

    public void setLastStepIndex(int stepListSize) {
        mLastStepIndex = stepListSize - 1;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        mIngredients = ingredients;
    }

    public void setStepIndex(int stepIndex) {
        mStepIndex = stepIndex;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(STEP_OBJECT, mStep);
        outState.putParcelableArrayList(INGREDIENT_LIST, mIngredients);
        outState.putInt(LAST_STEP_INDEX, mLastStepIndex);
        outState.putInt(STEP_INDEX, mStepIndex);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mTwoPane = RecipeActivity.getTwoPane();
        if (!mTwoPane) {
            try {
                mStepNavigationOnClickHandler = (StepNavigationOnClickHandler) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + " must implement StepNavigationOnClickHandler");
            }
        }

    }
}
