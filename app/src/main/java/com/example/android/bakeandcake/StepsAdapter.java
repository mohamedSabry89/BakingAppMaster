package com.example.android.bakeandcake;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private Context context;
    private ArrayList<Steps> steps = new ArrayList<>();

    public StepsAdapter(Context context, ArrayList<Steps> steps) {
        this.context = context;
        this.steps = steps;
    }

    @NonNull
    @Override
    public StepsAdapter.StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.steps_item_list, parent, false);
        return new StepsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.StepsViewHolder holder, int position) {

        //TextView tvSteps;
        Steps stepList = steps.get(position);
        //tvSteps = (TextView) holder.theSteps.findViewById(R.id.tv_Steps);
        holder.theSteps.setText(stepList.getShortDescription());
        Log.d("LOG", "lets see : " + stepList.getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (steps == null) {
            return 0;
        }
        return steps.size();
    }

    public static class StepsViewHolder extends RecyclerView.ViewHolder {
        TextView theSteps;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            theSteps = itemView.findViewById(R.id.tv_Steps);

        }
    }
}
