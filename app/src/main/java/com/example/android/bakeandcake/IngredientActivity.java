package com.example.android.bakeandcake;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientActivity extends AppCompatActivity {

    public IngredientsAdapter ingredientsAdapter;
    public RecyclerView recyclerView;
    public List<Ingredients> ingredientsArrayList = new ArrayList<>();
    public Context context;
    Component component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_layout);

        recyclerView = (RecyclerView) findViewById(R.id.rv_ingredients);

        Intent intent = getIntent();
        component = intent.getParcelableExtra("get_data");


    }
}