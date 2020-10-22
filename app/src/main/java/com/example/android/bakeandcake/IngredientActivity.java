package com.example.android.bakeandcake;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.bakeandcake.fragments.DetailsFragment;
import com.example.android.bakeandcake.fragments.StepsFragment;
import com.example.android.bakeandcake.models.Component;
import com.example.android.bakeandcake.models.Steps;

import java.util.ArrayList;

public class IngredientActivity extends AppCompatActivity implements DetailsFragment.OnRecipeClickListener {

    Component component;
    String ingredients;
    private boolean mTwoPane;
    ArrayList<Steps> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_activity);

        Intent intent = getIntent();
        if (intent != null) {
            ingredients = intent.getStringExtra(MainActivity.I_INGREDIENT_LIST_KEY);
            component = intent.getParcelableExtra(MainActivity.I_COMPONENT_LIST_KEY);
        }

        if (findViewById(R.id.steps_layout) != null) {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

        } else {
            mTwoPane = false;
        }

        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.B_INGREDIENT_LIST_KEY, ingredients);
        bundle.putParcelable(MainActivity.B_COMPONENT_LIST_KEY, component);

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

            bundle.putParcelableArrayList(MainActivity.B_ARRAY_STEPS_KEY, steps);
            bundle.putInt(MainActivity.B_POSITION_STEPS_KEY, position);

            StepsFragment stepsFragment = new StepsFragment();
            stepsFragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.steps_layout, stepsFragment)
                    .commit();

        } else {

            Bundle b = new Bundle();
            b.putParcelableArrayList(MainActivity.PANE_ARRAY_LIST_STEP, steps);

            final Intent intent = new Intent(this, StepsActivity.class);

            intent.putExtras(b);
            intent.putExtra(MainActivity.PANE_POSITION, position);
            startActivity(intent);
        }
    }
}