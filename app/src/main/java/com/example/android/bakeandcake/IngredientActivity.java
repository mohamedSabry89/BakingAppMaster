package com.example.android.bakeandcake;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.w3c.dom.Text;

public class IngredientActivity extends AppCompatActivity {

    private IngredientsAdapter ingredientsAdapter;
    private RecyclerView recyclerView;
    private List<Ingredients> ingredientsArrayList;
    private Context context;
    public RecyclerView.LayoutManager layoutManager;
    Ingredients ingredients;
    Component component;
    TextView qty, measure, desc;
    final static String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    String theMeasure, theDesc;
    int theQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_layout);

        qty = (TextView) findViewById(R.id.ingredients_quantity);
        measure = (TextView) findViewById(R.id.ingredients_measure);
        desc = (TextView) findViewById(R.id.ingredients_desc);



        //Intent intent = getIntent();
        //ingredientsArrayList = intent.getParcelableExtra("id");
        //ingredientsArrayList = component.getIngredientsList();

        //theQty = ingredients.getQuantity();
        //theMeasure = ingredients.getMeasure();
        //theDesc = ingredients.getIngredient();

        recyclerView = (RecyclerView) findViewById(R.id.rv_ingredients);

        new getIngredientsJson().execute();

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ingredientsAdapter = new IngredientsAdapter(context, ingredientsArrayList);
        recyclerView.setAdapter(ingredientsAdapter);
        Log.d("log", "what is wrong1 : " + ingredientsArrayList);

        //qty.setText(theQty);
        //measure.setText(theMeasure);
        //desc.setText(theDesc);

    }

    public class getIngredientsJson extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String urlSearchResult = null;
            Uri buildUri = Uri.parse(RECIPE_URL).buildUpon().build();
            try {
                URL url = new URL(buildUri.toString());
                urlSearchResult = getResponseFromHttpUrl(url);
                Log.d("log", "what is wrong3 : " + urlSearchResult);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return urlSearchResult;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                ingredientsArrayList = Arrays.asList(IngredientsJsonUtils.parseIngredientsJson(IngredientActivity.this, s));
                Log.d("log", "what is wrong2 : " + ingredientsArrayList);
                layoutManager = new LinearLayoutManager(IngredientActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                ingredientsAdapter = new IngredientsAdapter(IngredientActivity.this, ingredientsArrayList);
                recyclerView.setAdapter(ingredientsAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}