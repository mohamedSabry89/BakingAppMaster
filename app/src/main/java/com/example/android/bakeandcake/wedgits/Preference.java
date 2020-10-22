package com.example.android.bakeandcake.wedgits;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.bakeandcake.R;
import com.example.android.bakeandcake.models.Component;
import com.example.android.bakeandcake.models.Ingredients;
import com.google.gson.Gson;

class Preference {

    private static SharedPreferences pref;

    public static Component ingredients(Context context) {
        pref = context.getSharedPreferences("the_preference", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("preferences_key", "");
        return gson.fromJson(json, Component.class);
    }
}
