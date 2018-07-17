package com.example.android.bakingapp.adapters.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.clickHandlers.RecipeOnClickHandler;
import com.example.android.bakingapp.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.recipe_item_text_view)
    TextView mRecipeTextView;

    @BindView(R.id.recipe_item_image_view)
    ImageView mRecipeImageView;

    private RecipeOnClickHandler mRecipeOnClickHandler;
    private List<Recipe> mRecipes;

    public RecipeAdapterViewHolder(View itemView, RecipeOnClickHandler recipeOnClickHandler, List<Recipe> recipes) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mRecipeOnClickHandler = recipeOnClickHandler;
        mRecipes = recipes;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int adapterPosition = getAdapterPosition();
        mRecipeOnClickHandler.onRecipeClick(mRecipes.get(adapterPosition));
    }

    public void bind(Recipe recipe) {
        mRecipeTextView.setText(recipe.getName());

        if(!recipe.getImage().isEmpty()){
            Picasso.get()
                    .load(recipe.getImage())
                    .placeholder(R.drawable.art_cake)
                    .error(R.drawable.art_cake)
                    .into(mRecipeImageView);
        }
    }
}
