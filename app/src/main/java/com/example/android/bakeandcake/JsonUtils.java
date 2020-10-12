package com.example.android.bakeandcake;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Component[] parseComponentJson(Context context, String json) throws JSONException {

        final String URL_ = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        final String ID = "id";
        final String NAME = "name";

        JSONObject movieJson = new JSONObject(json);

        JSONObject theId = movieJson.getJSONObject(ID);
        JSONObject theName = movieJson.getJSONObject(NAME);

        return null;
    }
}
