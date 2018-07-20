package com.example.android.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.android.bakingapp.activities.MainActivity;
import com.example.android.bakingapp.activities.RecipeDetailActivity;
import com.example.android.bakingapp.models.Recipe;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, Recipe recipe) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);

        Intent intent;
        if(recipe.getId() == 0){
            intent = new Intent(context, MainActivity.class);
            views.setTextViewText(R.id.appwidget_text, widgetText);
        } else {
            views.setTextViewText(R.id.appwidget_text, recipe.getName());
            intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra(MainActivity.RECIPE_ENTITY, recipe);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.recipe_widget_provider, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void onUpdateRecipeWidget(Context context, AppWidgetManager appWidgetManager,
                                            int[] appWidgetIds, Recipe recipe) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipe);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        //todo check if is update every 30 minutes and set it to default
        RecipeWidgetService.startActionUpdateRecipeWidget(context, new Recipe());
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

