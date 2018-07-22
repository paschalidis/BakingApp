//package com.example.android.bakingapp;
//
//import android.content.Context;
//import android.content.Intent;
//import android.widget.RemoteViews;
//import android.widget.RemoteViewsService;
//
//import com.example.android.bakingapp.models.Ingredient;
//import com.example.android.bakingapp.models.Recipe;
//
//public class IngredientListRemoteViewsFactory2 implements RemoteViewsService.RemoteViewsFactory {
//
//    private Recipe mRecipe;
//    private Context mContext;
//
//    public IngredientListRemoteViewsFactory(Context context, Recipe recipe) {
//        mContext = context;
//        mRecipe = recipe;
//    }
//
//    @Override
//    public void onCreate() {
//
//    }
//
//    @Override
//    public void onDataSetChanged() {
//
//    }
//
//    @Override
//    public void onDestroy() {
//
//    }
//
//    @Override
//    public int getCount() {
//        if (mRecipe != null) {
//            return mRecipe.getIngredients().size();
//        }
//        return 0;
//    }
//
//    @Override
//    public RemoteViews getViewAt(int position) {
//        if (mRecipe == null) {
//            return null;
//        }
//
//        Ingredient ingredient = mRecipe.getIngredients().get(position);
//
//        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.recipe_ingredient_list_item);
//
//        views.setTextViewText(R.id.ingredient_ingredient_text_view, ingredient.getIngredient());
//        views.setTextViewText(R.id.ingredient_measure_text_view, ingredient.getMeasure());
//        views.setTextViewText(R.id.ingredient_quantity_text_view, ingredient.getQuantity());
//
//
//        return views;
//    }
//
//    @Override
//    public RemoteViews getLoadingView() {
//        return null;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 1;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 1;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//}
