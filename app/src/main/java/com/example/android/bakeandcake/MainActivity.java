package com.example.android.bakeandcake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecipeAdapter recipeAdapter;
    public RecyclerView recyclerView;
    public List<Component> componentList = new ArrayList<>();
    public Context context;

    private final static String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  com = new Component(0, "111", "222", "333");
        componentList.add(com);
        com = new Component(0, "333", "111", "222");
        componentList.add(com);
        com = new Component(0, "222", "333", "111");
        componentList.add(com);*/

        recyclerView = (RecyclerView) findViewById(R.id.rv_reciep);
        extractRecipeJson();
    }

    private void extractRecipeJson() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Component component = new Component();
                        component.setName(jsonObject.getString("name").toString());
                        component.setServings(jsonObject.getString("servings").toString());
                        componentList.add(component);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recipeAdapter = new RecipeAdapter(context, componentList);
                recyclerView.setAdapter(recipeAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse" + error.getMessage());
            }
        });
        requestQueue.add(jsonRequest);
    }
}