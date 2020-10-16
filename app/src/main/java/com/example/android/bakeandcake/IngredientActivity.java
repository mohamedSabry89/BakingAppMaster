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

    private StepsAdapter stepsAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Steps> steps;
    private Context context;
    public RecyclerView.LayoutManager layoutManager;
    TextView ingredientTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_layout);

        ingredientTextView = (TextView) findViewById(R.id.tv_ingredients);

        Intent intent = getIntent();
        String ingredients = intent.getStringExtra("ingredient_list");
        steps = intent.getParcelableArrayListExtra("steps_list");

        //new getIngredientsJson().execute();
        ingredientTextView.setText(ingredients);
        Log.d("log", "what is wrong1 : " + ingredients);

        recyclerView = findViewById(R.id.rv_steps);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        stepsAdapter = new StepsAdapter(context, steps);
        recyclerView.setAdapter(stepsAdapter);

    }


}