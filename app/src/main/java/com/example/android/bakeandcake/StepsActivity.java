package com.example.android.bakeandcake;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android.bakeandcake.fragments.StepsFragment;
import com.example.android.bakeandcake.models.Component;
import com.example.android.bakeandcake.models.Steps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class StepsActivity extends AppCompatActivity {

    Component component;
    Steps theSteps;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_activity);

        Intent intent = getIntent();
        if (intent != null) {
            theSteps = intent.getParcelableExtra("stepList");
            component = intent.getParcelableExtra("componentList");
            position = intent.getIntExtra("position", 0);
        }

        Log.d("LOG", "the Position is 22 : " + position);
        Bundle bundle1 = new Bundle();

        bundle1.putParcelable("steps_key", theSteps);
        bundle1.putParcelable("component_key", component);
        bundle1.putInt("position_key", position);

        StepsFragment stepsFragment = new StepsFragment();
        stepsFragment.setArguments(bundle1);
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        fragmentManager1.beginTransaction()
                .replace(R.id.ingredients_layout, stepsFragment)
                .commit();
    }
}
