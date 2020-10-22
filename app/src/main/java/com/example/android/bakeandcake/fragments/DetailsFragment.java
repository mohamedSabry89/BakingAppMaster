package com.example.android.bakeandcake.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakeandcake.MainActivity;
import com.example.android.bakeandcake.R;
import com.example.android.bakeandcake.adapters.StepsAdapter;
import com.example.android.bakeandcake.models.Component;

public class DetailsFragment extends Fragment {

    OnRecipeClickListener mCallback;

    public interface OnRecipeClickListener {
        void onRecipeSelected(int position);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnRecipeClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnRecipeClickListener");
        }
    }

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
            component = bundle.getParcelable(MainActivity.B_COMPONENT_LIST_KEY);
            ingredients = bundle.getString(MainActivity.B_INGREDIENT_LIST_KEY);
        }
        Log.d("LOG", "what is wrong :::" + ingredients);
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
