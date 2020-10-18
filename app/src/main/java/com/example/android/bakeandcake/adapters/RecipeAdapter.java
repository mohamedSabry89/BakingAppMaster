package com.example.android.bakeandcake.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakeandcake.IngredientActivity;
import com.example.android.bakeandcake.R;
import com.example.android.bakeandcake.models.Component;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<Component> components = new ArrayList<>();

    public RecipeAdapter(Context context, List<Component> components) {
        this.context = context;
        this.components = components;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.activity_main_list_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {

        TextView bakeName, servings;

        final Component component = components.get(position);
        final int id = component.getId();
        final String gettingIngredients = component.getIngredientsList();

        //ArrayList<Steps> theSteps = new ArrayList<>();
        //Steps steps = theSteps.get(position);
        //String desc = steps.getDescription();

        bakeName = (TextView) holder.nameTextView.findViewById(R.id.reciep_name);
        servings = (TextView) holder.servingTextView.findViewById(R.id.serving_number);

        bakeName.setText(component.getName());
        servings.setText(component.getServings());

        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), IngredientActivity.class);
                intent.putExtra("ingredient_list", gettingIngredients);
                intent.putExtra("steps_list", component.getStepsList());

                Log.d("log", "the id is : " + id);
                //Log.d("log", "the desc is : " + desc);
                Log.d("log", "the steps is : " + component.getStepsList());
                Log.d("log", "the ingredient is : " + gettingIngredients);
                Log.d("log", "getting Step List : " + component.getStepsList());

                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (components == null) {
            return 0;
        }
        return components.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, servingTextView;


        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.reciep_name);
            servingTextView = itemView.findViewById(R.id.serving_number);
        }
    }
}
