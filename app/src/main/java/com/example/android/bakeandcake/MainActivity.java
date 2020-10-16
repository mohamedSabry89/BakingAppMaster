package com.example.android.bakeandcake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
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
    private LinearLayoutManager layoutManager;
    public List<Component> componentList = new ArrayList<>();
    public Context context;
    private List<Ingredients> ingredientsArrayList;
    private final static String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    Ingredients ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_reciep);
        layoutManager = new LinearLayoutManager(this);
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
                        Ingredients ingredients = new Ingredients();
                        component.setId(jsonObject.getInt("id"));
                        component.setName(jsonObject.getString("name"));
                        component.setServings(jsonObject.getString("servings"));

                        String theIngredients = "";
                        JSONArray ingredientsJson = jsonObject.getJSONArray("ingredients");
                        for (int ing = 0; ing < ingredientsJson.length(); ing++) {
                            JSONObject ingObject = ingredientsJson.getJSONObject(ing);
                            Ingredients ingredients1 = new Ingredients();
                            ingredients1.setQuantity(ingObject.getDouble("quantity"));
                            ingredients1.setMeasure(ingObject.getString("measure"));
                            ingredients1.setIngredient(ingObject.getString("ingredient"));

                            theIngredients += (ingredients1.getQuantity() + " " +
                                    ingredients1.getMeasure() + "\t\t" + ingredients1.getIngredient() + "\n");
                        }
                        component.setIngredientsList(theIngredients);
                        Log.i("LOG", "The Ingredients List is : " + theIngredients);
                        componentList.add(component);
                        Log.d("tag", "onErrorResponse : " + ingredientsArrayList);
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