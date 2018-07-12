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
import com.example.android.bakingapp.models.Step;

public class RecipeDetailFragment extends Fragment {

    public static final String STEP_OBJECT = "step_object";

    // Tag for logging
    private static final String TAG = RecipeFragment.class.getSimpleName();

    private Step mStep;

    public RecipeDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mStep = savedInstanceState.getParcelable(STEP_OBJECT);
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);

        if (mStep != null) {
            TextView textView = rootView.findViewById(R.id.recipe_detail_description_text_view);
            textView.setText(mStep.getDescription());
        } else {
            Log.v(TAG, "This fragment has a null Step");
        }


        return rootView;
    }

    public void setStep(Step step) {
        mStep = step;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(STEP_OBJECT, mStep);
    }
}
