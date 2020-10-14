package com.example.android.bakeandcake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IViewHolder> {

    private Context context;
    private List<Ingredients> ingredientsList;

    public IngredientsAdapter(Context context, List<Ingredients> ingredientsList) {
        this.context = context;
        this.ingredientsList = ingredientsList;
    }

    @NonNull
    @Override
    public IViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.ingredients_list_item, parent, false);
        return new IViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IViewHolder holder, int position) {

        TextView qty, measure, ingredient;
        final Ingredients ingredients = ingredientsList.get(position);

        qty = (TextView) holder.tv1.findViewById(R.id.ingredients_quantity);
        measure = (TextView) holder.tv2.findViewById(R.id.ingredients_measure);
        ingredient = (TextView) holder.tv3.findViewById(R.id.ingredients_desc);

        qty.setText(ingredients.getQuantity());
        measure.setText(ingredients.getMeasure());
        ingredient.setText(ingredients.getIngredient());

    }

    @Override
    public int getItemCount() {
        if (ingredientsList == null) {
            return 0;
        }
        return ingredientsList.size();
    }

    public static class IViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3;

        public IViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.ingredients_quantity);
            tv2 = (TextView) itemView.findViewById(R.id.ingredients_measure);
            tv3 = (TextView) itemView.findViewById(R.id.ingredients_desc);
        }
    }
}
