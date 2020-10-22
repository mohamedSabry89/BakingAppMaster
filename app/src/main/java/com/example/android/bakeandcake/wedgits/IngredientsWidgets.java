package com.example.android.bakeandcake.wedgits;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakeandcake.BuildConfig;
import com.example.android.bakeandcake.R;
import com.example.android.bakeandcake.models.Component;
import com.example.android.bakeandcake.models.Ingredients;

import java.util.List;

public class IngredientsWidgets extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientRemoteViewsFactory(this.getApplicationContext());
    }
}

class IngredientRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private Component component;
    private List<Ingredients> ingredients;

    public IngredientRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        component = Preference.ingredients(mContext);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.remote_view);
        remoteViews.setTextViewText(R.id.remotewidget_baking_measure, ingredients.get(position).getMeasure());
        remoteViews.setTextViewText(R.id.remotewidget_baking_ingredientlist, ingredients.get(position).getIngredient());
        remoteViews.setTextViewText(R.id.remotewidget_baking_quantity, String.valueOf(ingredients.get(position).getQuantity()));
        return remoteViews;
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
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
