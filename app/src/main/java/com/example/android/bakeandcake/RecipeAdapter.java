package com.example.android.bakeandcake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Context context;
    private List<Component> components;

    public RecipeAdapter(Context context, List<Component> components) {
        this.context = context;
        this.components = components;
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recipe_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        TextView bakeName, servings;
        final Component component = components.get(position);
        bakeName = (TextView) holder.nameTextView.findViewById(R.id.reciep_name);
        servings = (TextView) holder.servingTextView.findViewById(R.id.serving_number);
        bakeName.setText(component.getName());
        servings.setText(component.getServings());
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView servingTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.reciep_name);
            servingTextView = itemView.findViewById(R.id.serving_number);
        }
    }
}
