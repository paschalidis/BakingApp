package com.example.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.activities.MainActivity;
import com.example.android.bakingapp.models.Ingredient;
import com.example.android.bakingapp.models.Recipe;

public class IngredientListWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientListRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    //    @Override
//    public RemoteViewsFactory onGetViewFactory(Intent intent) {
//        return new IngredientListRemoteViewsFactory(this.getApplicationContext(), intent);
//    }
}

class IngredientListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Recipe mRecipe;
    private Context mContext;

    public IngredientListRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;

        mRecipe = intent.getBundleExtra("bundle").getParcelable(MainActivity.RECIPE_ENTITY);

//        if(intent.hasExtra("bundle")){
//            Bundle bundle = intent.getBundleExtra("bundle");
//            mRecipe = bundle.getParcelable(MainActivity.RECIPE_ENTITY);
//        }
//        if (intent.hasExtra(MainActivity.RECIPE_ENTITY)) {
//            mRecipe = intent.getParcelableExtra(MainActivity.RECIPE_ENTITY);
//        }
    }

    @Override
    public void onCreate() {
        int i = 0;
    }

    @Override
    public void onDataSetChanged() {

        int i = 3;
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
//        views.setTextViewText(R.id.ingredient_measure_text_view, ingredient.getMeasure());
//        views.setTextViewText(R.id.ingredient_quantity_text_view, ingredient.getQuantity());


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

    public void setRecipe(Recipe recipe) {
        mRecipe = recipe;
    }
}
