package com.example.android.bakingapp.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class IngredientListWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientListRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}