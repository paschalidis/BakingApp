package com.example.android.bakingapp.adapters.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Ingredient;

public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {

    private final TextView mIngredientTextView;
    private final TextView mMeasureTextView;
    private final TextView mQuantityTextView;

    public IngredientAdapterViewHolder(View itemView) {
        super(itemView);
        mIngredientTextView = itemView.findViewById(R.id.ingredient_ingredient_text_view);
        mMeasureTextView = itemView.findViewById(R.id.ingredient_measure_text_view);
        mQuantityTextView = itemView.findViewById(R.id.ingredient_quantity_text_view);

    }

    public void bind(Ingredient ingredient) {
        mIngredientTextView.setText(ingredient.getIngredient());
        mMeasureTextView.setText(ingredient.getMeasure());
        mQuantityTextView.setText(ingredient.getQuantity());
    }
}
