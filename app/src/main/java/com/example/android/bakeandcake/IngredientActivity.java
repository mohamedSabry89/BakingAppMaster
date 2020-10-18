package com.example.android.bakeandcake;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakeandcake.adapters.StepsAdapter;
import com.example.android.bakeandcake.fragments.StepsFragment;
import com.example.android.bakeandcake.models.Component;
import com.example.android.bakeandcake.models.Steps;

public class IngredientActivity extends AppCompatActivity {

    private StepsAdapter stepsAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Steps> steps;
    private Context context;
    public RecyclerView.LayoutManager layoutManager;
    TextView ingredientTextView;
    Component component;
    Steps theSteps;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

        ingredientTextView = (TextView) findViewById(R.id.tv_ingredients);

        Intent intent = getIntent();
        String ingredients = intent.getStringExtra("ingredient_list");

        steps = intent.getParcelableArrayListExtra("steps_list");

        ingredientTextView.setText(ingredients);
        Log.d("log", "what is wrong1 : " + ingredients);

        recyclerView = findViewById(R.id.rv_steps);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        stepsAdapter = new StepsAdapter(context, steps);
        recyclerView.setAdapter(stepsAdapter);

        /*StepsFragment stepsFragment = new StepsFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.steps_detail_fragment, stepsFragment)
                .commit();*/
    }


}