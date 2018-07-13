package com.example.android.bakingapp.adapters.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.clickHandlers.StepOnClickHandler;
import com.example.android.bakingapp.models.Step;

import java.util.List;

public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView mStepTextView;
    private StepOnClickHandler mStepClickHandler;
    private List<Step> mSteps;

    public StepAdapterViewHolder(View itemView, StepOnClickHandler stepOnClickHandler, List<Step> steps) {
        super(itemView);
        mStepTextView = (TextView) itemView.findViewById(R.id.recipe_step_text_view);
        mStepClickHandler = stepOnClickHandler;
        mSteps = steps;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int adapterPosition = getAdapterPosition();
        mStepClickHandler.onStepClick(mSteps.get(adapterPosition));
    }

    public void bind(String recipeTitle) {
        mStepTextView.setText(recipeTitle);
    }
}
