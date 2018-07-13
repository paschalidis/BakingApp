package com.example.android.bakingapp.adapters.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.clickHandlers.RecipeOnClickHandler;
import com.example.android.bakingapp.models.Recipe;

import java.util.List;

public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView mRecipeTextView;

    private RecipeOnClickHandler mRecipeOnClickHandler;
    private List<Recipe> mRecipes;

    public RecipeAdapterViewHolder(View itemView, RecipeOnClickHandler recipeOnClickHandler, List<Recipe> recipes) {
        super(itemView);
        mRecipeTextView = (TextView) itemView.findViewById(R.id.recipe_item_text_view);
        mRecipeOnClickHandler = recipeOnClickHandler;
        mRecipes = recipes;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int adapterPosition = getAdapterPosition();
        mRecipeOnClickHandler.onRecipeClick(mRecipes.get(adapterPosition));
    }

    public void bind(String recipeTitle) {
        mRecipeTextView.setText(recipeTitle);
    }
}
