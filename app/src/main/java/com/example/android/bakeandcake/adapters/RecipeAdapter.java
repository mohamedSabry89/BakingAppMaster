package com.example.android.bakeandcake.adapters;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakeandcake.IngredientActivity;
import com.example.android.bakeandcake.MainActivity;
import com.example.android.bakeandcake.R;
import com.example.android.bakeandcake.models.Component;
import com.example.android.bakeandcake.wedgits.TheWedgitServices;
import com.google.gson.Gson;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    SharedPreferences sharedPreferences;
    private Context context;
    private ArrayList<Component> components;

    public RecipeAdapter(Context context, ArrayList<Component> components) {
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {

        TextView bakeName, servings;

        final Component component = components.get(position);
        final int id = component.getId();
        final String gettingIngredients = component.getIngredientsList();

        bakeName = (TextView) holder.nameTextView.findViewById(R.id.reciep_name);
        servings = (TextView) holder.servingTextView.findViewById(R.id.serving_number);

        bakeName.setText(component.getName());
        servings.setText(component.getServings());

        holder.itemView.setOnClickListener(view -> {


            sharedPreferences = view.getContext().getSharedPreferences(MainActivity.SHARED_PREFERENCE_KEY, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            Gson gson = new Gson();

            String json = gson.toJson(component);
            editor.putString(MainActivity.SHARED_PREFERENCE_GSON_KEY, json);
            editor.putString(MainActivity.PREFERENCE_RECIPE_NAME, component.getName());
            editor.putInt(MainActivity.PREFERENCE_RECIPE_ID, component.getId());
            editor.apply();

            TheWedgitServices.startActionShowRecipes(view.getContext());

            Intent intent = new Intent(view.getContext(), IngredientActivity.class);

            intent.putExtra(MainActivity.I_INGREDIENT_LIST_KEY, gettingIngredients);
            intent.putExtra(MainActivity.I_COMPONENT_LIST_KEY, component);
            intent.putExtra(MainActivity.I_COMPONENT_STEP_LIST, component.getStepsList());

            view.getContext().startActivity(intent);

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
