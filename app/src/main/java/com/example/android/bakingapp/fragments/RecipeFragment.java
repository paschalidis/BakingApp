package com.example.android.bakingapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Recipe;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment {

    private Recipe mRecipe;

    public RecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);

        if (mRecipe != null) {
            TextView textView = rootView.findViewById(R.id.recipe_name_text_view);
            textView.setText(mRecipe.getName());
        }


        return rootView;
    }

    public void setRecipe(Recipe recipe) {
        mRecipe = recipe;
    }
}
