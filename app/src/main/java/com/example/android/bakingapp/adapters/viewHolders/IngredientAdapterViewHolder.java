package com.example.android.bakingapp.adapters.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Ingredient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ingredient_ingredient_text_view)
    TextView mIngredientTextView;

    @BindView(R.id.ingredient_measure_text_view)
    TextView mMeasureTextView;

    @BindView(R.id.ingredient_quantity_text_view)
    TextView mQuantityTextView;

    public IngredientAdapterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bind(Ingredient ingredient) {
        mIngredientTextView.setText(ingredient.getIngredient());
        mMeasureTextView.setText(ingredient.getMeasure());
        mQuantityTextView.setText(ingredient.getQuantity());
    }
}
