package com.example.android.bakeandcake;

import android.content.Intent;
import android.os.Bundle;

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
            position = intent.getIntExtra("position", 0);
            steps = intent.getParcelableArrayListExtra("array_list_steps");
        }

        Bundle bundle1 = new Bundle();

        bundle1.putParcelableArrayList("array_steps_key", steps);
        bundle1.putInt("position_key", position);

        StepsFragment stepsFragment = new StepsFragment();
        stepsFragment.setArguments(bundle1);
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        fragmentManager1.beginTransaction()
                .replace(R.id.steps_layout, stepsFragment)
                .commit();

    }
}