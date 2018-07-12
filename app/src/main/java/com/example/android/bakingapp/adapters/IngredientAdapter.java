package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.viewHolders.IngredientAdapterViewHolder;
import com.example.android.bakingapp.models.Ingredient;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapterViewHolder> {

    private Context mContext;
    private ArrayList<Ingredient> mIngredients;

    public IngredientAdapter(Context context, ArrayList<Ingredient> ingredientData) {
        mContext = context;
        mIngredients = ingredientData;
    }

    @NonNull
    @Override
    public IngredientAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdIngredientListItem = R.layout.recipe_ingredient_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdIngredientListItem, parent, false);

        return new IngredientAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapterViewHolder holder, int position) {
        holder.bind(mIngredients.get(position));
    }

    @Override
    public int getItemCount() {
        if(mIngredients == null){
            return 0;
        }
        return mIngredients.size();
    }

}
