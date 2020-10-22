package com.example.android.bakeandcake;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android.bakeandcake.fragments.StepsFragment;
import com.example.android.bakeandcake.models.Steps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class StepsActivity extends AppCompatActivity {

    int position;
    ArrayList<Steps> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.steps_activity);

        Intent intent = getIntent();
        if (intent != null) {
            position = intent.getIntExtra(MainActivity.PANE_POSITION, 0);
            steps = intent.getParcelableArrayListExtra(MainActivity.PANE_ARRAY_LIST_STEP);
        }

        Bundle bundle1 = new Bundle();

        bundle1.putParcelableArrayList(MainActivity.B_ARRAY_STEPS_KEY, steps);
        bundle1.putInt(MainActivity.B_POSITION_STEPS_KEY, position);

        StepsFragment stepsFragment = new StepsFragment();
        stepsFragment.setArguments(bundle1);
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        fragmentManager1.beginTransaction()
                .replace(R.id.steps_layout, stepsFragment)
                .commit();

    }
}