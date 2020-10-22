package com.example.android.bakeandcake;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.bakeandcake.fragments.DetailsFragment;
import com.example.android.bakeandcake.fragments.StepsFragment;
import com.example.android.bakeandcake.models.Component;
import com.example.android.bakeandcake.models.Steps;

import java.util.ArrayList;

public class IngredientActivity extends AppCompatActivity implements DetailsFragment.OnRecipeClickListener {

    Component component;
    Steps theSteps;
    String ingredients;
    private boolean mTwoPane;
    ArrayList<Steps> steps;

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

        if (findViewById(R.id.steps_layout) != null) {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

        } else {
            mTwoPane = false;
        }

        Bundle bundle = new Bundle();
        bundle.putString("ingredients_key", ingredients);
        bundle.putParcelable("component_key", component);
        bundle.putParcelable("steps_key", theSteps);

        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.ingredients_layout, detailsFragment)
                .commit();

    }

    @Override
    public void onRecipeSelected(int position) {

        if (mTwoPane) {

            Bundle bundle = new Bundle();

            bundle.putParcelableArrayList("array_steps_key", steps);
            bundle.putInt("position_key", position);

            StepsFragment stepsFragment = new StepsFragment();
            stepsFragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.steps_layout, stepsFragment)
                    .commit();

        } else {

            Bundle b = new Bundle();
            b.putParcelableArrayList("array_list_steps", steps);

            final Intent intent = new Intent(this, StepsActivity.class);

            intent.putExtras(b);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }
}