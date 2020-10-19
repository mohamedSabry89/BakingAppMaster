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
import com.example.android.bakeandcake.fragments.DetailsFragment;
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
    String ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_activity);

        Intent intent = getIntent();
        if (intent != null) {
            ingredients = intent.getStringExtra("ingredient_list_key");
            component = intent.getParcelableExtra("component_list_key");
            theSteps = intent.getParcelableExtra("step_list_key");
        }

        // Log.d("LOG", " what is the steps 1" + theSteps.getShortDescription());


        Bundle bundle = new Bundle();
        bundle.putString("ingredients_key", ingredients);
        bundle.putParcelable("component_key", component);
        bundle.putParcelable("steps_key", theSteps);

        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.ingredients_layout, detailsFragment)
                .commit();

        Bundle bundle1 = new Bundle();
        bundle1.putParcelable("steps_key", theSteps);
        bundle1.putParcelable("component_key", component);

        StepsFragment stepsFragment = new StepsFragment();
        stepsFragment.setArguments(bundle1);
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        fragmentManager1.beginTransaction()
                .add(R.id.ingredients_layout, stepsFragment)
                .commit();
    }

}