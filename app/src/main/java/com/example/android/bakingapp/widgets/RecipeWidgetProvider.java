package com.example.android.bakingapp.widgets;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.MainActivity;
import com.example.android.bakingapp.activities.RecipeActivity;
import com.example.android.bakingapp.models.Recipe;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    public static String BUNDLE_EXTRA = "bundle_extra";
    public static Recipe mRecipe;
    private final static int MAX_RECIPE_SINGLE_WIDGET_VIEW = 200;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
        int width = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        RemoteViews remoteViews;

        if (width < MAX_RECIPE_SINGLE_WIDGET_VIEW) {
            remoteViews = getRecipeView(context);
        } else {
            remoteViews = getIngredientListView(context);
        }

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        RecipeWidgetService.startActionUpdateRecipeWidget(context, mRecipe);
    }

    private static RemoteViews getRecipeView(Context context) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
        views.setViewVisibility(R.id.widget_resize_text, View.GONE);

        Intent intent;
        if (mRecipe == null) {
            intent = new Intent(context, MainActivity.class);
            views.setTextViewText(R.id.appwidget_text, widgetText);
        } else {
            views.setTextViewText(R.id.appwidget_text, mRecipe.getName());
            views.setViewVisibility(R.id.widget_resize_text, View.VISIBLE);
            intent = new Intent(context, RecipeActivity.class);
            intent.putExtra(MainActivity.RECIPE_ENTITY, mRecipe);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.recipe_widget_provider, pendingIntent);

        return views;
    }

    private static RemoteViews getIngredientListView(Context context) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_list_recipe_ingredient);

        Intent intent = new Intent(context, IngredientListWidgetService.class);

        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.RECIPE_ENTITY, mRecipe);

        intent.putExtra(BUNDLE_EXTRA, bundle);

        views.setRemoteAdapter(R.id.widget_recipe_ingredient_list, intent);

        Intent appIntent = new Intent(context, RecipeActivity.class);
        appIntent.putExtra(MainActivity.RECIPE_ENTITY, mRecipe);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setTextViewText(R.id.app_widget_list_text_view, mRecipe.getName());
        views.setOnClickPendingIntent(R.id.app_widget_list_text_view, pendingIntent);

        views.setEmptyView(R.id.widget_recipe_ingredient_list, R.id.empty_view);

        return views;
    }

    public static void onUpdateRecipeWidget(Context context, AppWidgetManager appWidgetManager,
                                            int[] appWidgetIds, Recipe recipe) {
        mRecipe = recipe;
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RecipeWidgetService.startActionUpdateRecipeWidget(context, mRecipe);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

