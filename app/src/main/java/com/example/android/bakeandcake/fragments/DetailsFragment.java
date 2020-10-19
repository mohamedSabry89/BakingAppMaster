package com.example.android.bakeandcake.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakeandcake.R;
import com.example.android.bakeandcake.adapters.StepsAdapter;
import com.example.android.bakeandcake.models.Component;
import com.example.android.bakeandcake.models.Steps;

import java.util.ArrayList;

public class DetailsFragment extends Fragment {

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

    public DetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ingredientTextView = (TextView) rootView.findViewById(R.id.tv_ingredients);

        Bundle bundle = getArguments();
        if (bundle != null) {
            ingredients = bundle.getString("ingredients_key");
            theSteps = bundle.getParcelable("step_key");
            component = bundle.getParcelable("component_key");
        }

        //Log.d("LOG", " what is the steps" + theSteps.getShortDescription());

        ingredientTextView.setText(ingredients);

        recyclerView = rootView.findViewById(R.id.rv_steps);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        stepsAdapter = new StepsAdapter(context, component.getStepsList());
        recyclerView.setAdapter(stepsAdapter);

        return rootView;
    }
}
