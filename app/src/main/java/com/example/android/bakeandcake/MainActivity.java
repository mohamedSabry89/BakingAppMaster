package com.example.android.bakeandcake;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
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
    public ArrayList<Component> componentList = new ArrayList<>();
    public Context context;
    private final static String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    ArrayList<ArrayList<Steps>> gettingSteps = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_reciep);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
                        component.setId(jsonObject.getInt("id"));
                        component.setName(jsonObject.getString("name"));
                        component.setServings(jsonObject.getString("servings"));

                        StringBuilder theIngredients = new StringBuilder();
                        JSONArray ingredientsJson = jsonObject.getJSONArray("ingredients");
                        for (int ing = 0; ing < ingredientsJson.length(); ing++) {
                            JSONObject ingObject = ingredientsJson.getJSONObject(ing);
                            Ingredients ingredients1 = new Ingredients();
                            ingredients1.setQuantity(ingObject.getDouble("quantity"));
                            ingredients1.setMeasure(ingObject.getString("measure"));
                            ingredients1.setIngredient(ingObject.getString("ingredient"));

                            theIngredients.append(ingredients1.getQuantity()).append(" ").append(ingredients1.getMeasure()).append("\t\t").append(ingredients1.getIngredient()).append("\n");
                        }
                        component.setIngredientsList(theIngredients.toString());

                        ArrayList<Steps> theSteps = new ArrayList<>();
                        JSONArray stepsJson = jsonObject.getJSONArray("steps");
                        for (int s = 0; s < stepsJson.length(); s++) {

                            JSONObject stepsObject = stepsJson.getJSONObject(s);
                            Steps steps = new Steps();
                            steps.setStepsId(stepsObject.getInt("id"));
                            steps.setShortDescription(stepsObject.getString("shortDescription"));
                            steps.setDescription(stepsObject.getString("description"));
                            steps.setVideoURL(stepsObject.getString("videoURL"));
                            steps.setThumbnailURL(stepsObject.getString("thumbnailURL"));
                            Log.d("tag", "The Step Description List Is : " + steps.getShortDescription());
                            theSteps.add(steps);

                        }
                        component.setStepsList(theSteps);
                        Log.d("tag", "The Step List Is : " + component.getStepsList());
                        componentList.add(component);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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