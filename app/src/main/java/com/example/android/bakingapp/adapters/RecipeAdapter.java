package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.viewHolders.RecipeAdapterViewHolder;
import com.example.android.bakingapp.clickHandlers.RecipeOnClickHandler;
import com.example.android.bakingapp.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapterViewHolder> {

    private Context mContext;
    private List<Recipe> mRecipes;
    private final RecipeOnClickHandler mRecipeClickHandler;

    public RecipeAdapter(Context context, RecipeOnClickHandler clickHandler) {
        mContext = context;
        mRecipes = new ArrayList<>();
        mRecipeClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdRecipeListItem = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdRecipeListItem, parent, false);

        return new RecipeAdapterViewHolder(view, mRecipeClickHandler, mRecipes);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapterViewHolder holder, int position) {
        holder.bind(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        if (mRecipes == null) {
            return 0;
        }
        return mRecipes.size();
    }

    public void setRecipeData(List<Recipe> recipeData) {
        mRecipes = recipeData;
        notifyDataSetChanged();
    }
}
