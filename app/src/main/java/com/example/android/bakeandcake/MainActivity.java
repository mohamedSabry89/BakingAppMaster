package com.example.android.bakeandcake;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.bakeandcake.adapters.RecipeAdapter;
import com.example.android.bakeandcake.models.Component;
import com.example.android.bakeandcake.models.Ingredients;
import com.example.android.bakeandcake.models.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // keys for intents and bundles (I for intent) & (B for bundle)
    public final static String I_INGREDIENT_LIST_KEY = "ingredient_list_key";
    public final static String I_COMPONENT_LIST_KEY = "component_list_key";

    public final static String B_INGREDIENT_LIST_KEY = "ingredient_key";
    public final static String B_COMPONENT_LIST_KEY = "component_key";

    public final static String B_ARRAY_STEPS_KEY = "array_steps_key";
    public final static String B_POSITION_STEPS_KEY = "position_key";

    public final static String PANE_POSITION = "position";
    public final static String PANE_ARRAY_LIST_STEP = "array_list_steps";

    public final static String SHARED_PREFERENCE_KEY = "the_preference";
    public final static String SHARED_PREFERENCE_GSON_KEY = "preferences_key";
    public final static String PREFERENCE_RECIPE_NAME = "recipe_name";
    public final static String PREFERENCE_RECIPE_ID = "recipe_id";

    public final static String I_COMPONENT_STEP_LIST = "get_component_step_list";

    public RecipeAdapter recipeAdapter;
    public RecyclerView recyclerView;
    public ArrayList<Component> componentList = new ArrayList<>();
    public Context context;
    private final static String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    public SimpleIdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_reciep);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        extractRecipeJson();
    }

    private void extractRecipeJson() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, URL, null, response -> {
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

                        theIngredients.append(ingredients1.getQuantity()).append(" ").append(ingredients1.getMeasure()).append("\n").append(ingredients1.getIngredient()).append("\n");
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
                        theSteps.add(steps);

                    }
                    component.setStepsList(theSteps);
                    componentList.add(component);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            recipeAdapter = new RecipeAdapter(context, componentList);
            recyclerView.setAdapter(recipeAdapter);
        }, error -> Log.d("tag", "onErrorResponse" + error.getMessage()));
        requestQueue.add(jsonRequest);
    }
}