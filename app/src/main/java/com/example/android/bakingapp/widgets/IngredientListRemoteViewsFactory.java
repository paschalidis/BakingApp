package com.example.android.bakingapp.widgets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.MainActivity;
import com.example.android.bakingapp.models.Ingredient;
import com.example.android.bakingapp.models.Recipe;

public class IngredientListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Recipe mRecipe;
    private Context mContext;

    public IngredientListRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        if (intent.hasExtra(RecipeWidgetProvider.BUNDLE_EXTRA)) {
            Bundle bundle = intent.getBundleExtra(RecipeWidgetProvider.BUNDLE_EXTRA);
            mRecipe = bundle.getParcelable(MainActivity.RECIPE_ENTITY);
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        mRecipe = RecipeWidgetProvider.mRecipe;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (mRecipe != null) {
            return mRecipe.getIngredients().size();
        }
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (mRecipe == null) {
            return null;
        }

        Ingredient ingredient = mRecipe.getIngredients().get(position);

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_ingredient_item_view);

        views.setTextViewText(R.id.widget_ingredient_text_view, ingredient.getIngredient());
        views.setTextViewText(R.id.widget_quantity_text_view, ingredient.getQuantity());
        views.setTextViewText(R.id.widget_measure_text_view, ingredient.getMeasure());

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

