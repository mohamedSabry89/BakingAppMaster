package com.example.android.bakeandcake.wedgits;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.bakeandcake.MainActivity;
import com.example.android.bakeandcake.models.Component;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class Preference {

    public static ArrayList<Component> componentList(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MainActivity.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString(MainActivity.SHARED_PREFERENCE_GSON_KEY, "");
        return gson.fromJson(json, (Type) Component.class);
    }
}
