package com.example.android.bakeandcake;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientActivity extends AppCompatActivity {

    private IngredientsAdapter ingredientsAdapter;
    private RecyclerView recyclerView;
    private List<Ingredients> ingredientsArrayList = new ArrayList<>();
    private Context context;
    private RecyclerView.LayoutManager layoutManager;
    Ingredients ingredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_layout);

        Intent intent = getIntent();
        ingredients = intent.getParcelableExtra("id");

        recyclerView = (RecyclerView) findViewById(R.id.rv_ingredients);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



    }
}