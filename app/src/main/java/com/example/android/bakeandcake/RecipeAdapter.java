package com.example.android.bakeandcake;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
        View itemView = inflater.inflate(R.layout.recipe_list_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {

        TextView bakeName, servings;

        final Component component = components.get(position);
        final int id = component.getId();
        //final List<Ingredients> ingredients = component.getIngredientsList();

        bakeName = (TextView) holder.nameTextView.findViewById(R.id.reciep_name);
        servings = (TextView) holder.servingTextView.findViewById(R.id.serving_number);

        bakeName.setText(component.getName());
        servings.setText(component.getServings());

        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), IngredientActivity.class);
                //intent.putExtra("id", (Parcelable) ingredients);
                Log.d("log", "the id is : " + id);
                Log.d("log", "the id is : " + position);
                //Log.d("log", "the id is : " + ingredients);

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

    public void setMovies(List<Component> componentList) {
        this.components = componentList;
        notifyDataSetChanged();
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
