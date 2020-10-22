package com.example.android.bakeandcake.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakeandcake.R;
import com.example.android.bakeandcake.StepsActivity;
import com.example.android.bakeandcake.models.Steps;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private Context context;
    private ArrayList<Steps> steps;
    //private ListItemClickListener mOnClickListener;

    public StepsAdapter(Context context, ArrayList<Steps> steps/* ListItemClickListener mOnClickListener*/) {
        this.context = context;
        this.steps = steps;
        //this.mOnClickListener = mOnClickListener;
    }

   /* public interface ListItemClickListener {
        void onListItemClick(int mDataset);
    } */

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

        Steps stepList = steps.get(position);
        int thePosition = stepList.getStepsId();

        holder.theSteps.setText(stepList.getShortDescription());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), StepsActivity.class);
            intent.putExtra("stepList", stepList);
            intent.putExtra("position", thePosition);
            intent.putExtra("array_list_steps", steps);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (steps == null) {
            return 0;
        }
        return steps.size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {
        TextView theSteps;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            theSteps = itemView.findViewById(R.id.tv_Steps);
        }

       /* @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnClickListener.onListItemClick(position);
        } */
    }
}
