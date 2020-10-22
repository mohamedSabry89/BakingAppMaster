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

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        //CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);
        SharedPreferences pref = context.getSharedPreferences("the_preference", Context.MODE_PRIVATE);
        String recipeName = pref.getString("recipe_name", "");
        views.setTextViewText(R.id.recipe_name, recipeName);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        TheWedgitServices.startActionShowRecipes(context);
    }

    public static void updateWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private static RemoteViews getIngredientsGridRemoteView(Context context){
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);

        // Set the IngredientWidgetService intent to act as the adapter for the view
        Intent intent = new Intent(context, IngredientsWidgets.class);
        views.setRemoteAdapter(R.id.widget_baking_ingredientlist, intent);

        // Set the RecipeDeListActivity intent to launch when clicked
        Intent appIntent = new Intent(context, MainActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_baking_ingredientlist, appPendingIntent);

        // Handle no data
        views.setEmptyView(R.id.wedgit_linear,0);

        return views;
    }
}

