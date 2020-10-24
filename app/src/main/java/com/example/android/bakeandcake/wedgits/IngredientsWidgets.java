package com.example.android.bakeandcake.wedgits;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakeandcake.R;
import com.example.android.bakeandcake.models.Component;

import java.util.ArrayList;

public class IngredientsWidgets extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientRemoteViewsFactory(this.getApplicationContext());
    }

}

class IngredientRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private ArrayList<Component> componentArrayList;
    private Component component;

    public IngredientRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        component = Preference.componentList(mContext);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.baking_app_widget);
        remoteViews.setTextViewText(R.id.widget_baking_ingredientlist, component.getIngredientsList());

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

