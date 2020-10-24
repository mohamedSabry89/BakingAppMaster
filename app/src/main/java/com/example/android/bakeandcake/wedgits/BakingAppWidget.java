package com.example.android.bakeandcake.wedgits;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;

import com.example.android.bakeandcake.MainActivity;
import com.example.android.bakeandcake.R;


public class BakingAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        RemoteViews views = getIngredientsGridRemoteView(context);

        SharedPreferences pref = context.getSharedPreferences(MainActivity.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String recipeName = pref.getString(MainActivity.PREFERENCE_RECIPE_NAME, "");
        views.setTextViewText(R.id.recipe_name, recipeName);
        //String ingredients = pref.getString(MainActivity.PREFERENCE_RECIPE_ID, "");
        //views.setTextViewText(R.id.remote_widget_baking_ingredient_list, ingredients);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        TheWedgitServices.startActionShowRecipes(context);
    }

    public static void updateWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private static RemoteViews getIngredientsGridRemoteView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.remote_view);

        Intent intent = new Intent(context, IngredientsWidgets.class);
        views.setRemoteAdapter(R.id.remote_widget_baking_ingredient_list, intent);

        Intent appIntent = new Intent(context, MainActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.remote_widget_baking_ingredient_list, appPendingIntent);

        views.setEmptyView(R.id.remote_view_layout, 0);

        return views;
    }
}

