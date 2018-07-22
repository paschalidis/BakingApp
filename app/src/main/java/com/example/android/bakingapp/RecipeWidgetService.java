package com.example.android.bakingapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;

import com.example.android.bakingapp.models.Recipe;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class RecipeWidgetService extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_UPDATE_RECIPE_WIDGET = "com.example.android.bakingapp.action.update_recipe_widget";

    private static final String EXTRA_PARAM_RECIPE = "com.example.android.bakingapp.extra.param_recipe";

    public RecipeWidgetService() {
        super("RecipeWidgetService");
    }

    /**
     * Starts this service to perform action Update Recipe Widget with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdateRecipeWidget(Context context, Recipe recipe) {
        Intent intent = new Intent(context, RecipeWidgetService.class);
        intent.setAction(ACTION_UPDATE_RECIPE_WIDGET);
        intent.putExtra(EXTRA_PARAM_RECIPE, recipe);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_RECIPE_WIDGET.equals(action)) {
                final Recipe recipe = intent.getParcelableExtra(EXTRA_PARAM_RECIPE);
                handleActionUpdateRecipeWidget(recipe);
            }
        }
    }

    /**
     * Handle action Update Recipe Widget in the provided background thread with the provided
     * parameters.
     */
    private void handleActionUpdateRecipeWidget(Recipe recipe) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidgetProvider.class));

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list_recipe_ingredient);

        RecipeWidgetProvider.onUpdateRecipeWidget(this, appWidgetManager, appWidgetIds, recipe);
    }
}
