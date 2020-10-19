package com.example.android.bakeandcake.fragments;

import android.os.Bundle;
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

public class DetailsFragment extends Fragment {

    public RecyclerView.LayoutManager layoutManager;
    TextView ingredientTextView;

    Component component;
    String ingredients;

    public DetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ingredientTextView = rootView.findViewById(R.id.tv_ingredients);

        Bundle bundle = getArguments();
        if (bundle != null) {
            component = bundle.getParcelable("component_key");
            ingredients = bundle.getString("ingredients_key");
        }

        ingredientTextView.setText(ingredients);

        RecyclerView recyclerView = rootView.findViewById(R.id.rv_steps);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        StepsAdapter stepsAdapter = new StepsAdapter(getContext(), component.getStepsList());
        recyclerView.setAdapter(stepsAdapter);

        return rootView;
    }
}
