package com.example.android.bakeandcake.wedgits;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.android.bakeandcake.R;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TheWedgitServices extends IntentService {

    private Context context;
    public static final String ACTION_SHOW_RECIPES = "com.example.android.bakeandcake.action.show_component";

    public TheWedgitServices() {
        super("TheWedgitServices");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void startActionShowRecipes(Context context) {
        Intent intent = new Intent(context, TheWedgitServices.class);
        intent.setAction(ACTION_SHOW_RECIPES);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SHOW_RECIPES.equals(action)) {
                handleActionShowRecipes();
            }
        }
    }

    private void handleActionShowRecipes() {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppWidget.class));

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.remote_widget_baking_ingredient_list);

        //Now update all widgets
        BakingAppWidget.updateWidgets(this, appWidgetManager, appWidgetIds);
    }
}
