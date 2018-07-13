package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.viewHolders.StepAdapterViewHolder;
import com.example.android.bakingapp.clickHandlers.StepOnClickHandler;
import com.example.android.bakingapp.models.Step;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapterViewHolder> {

    private Context mContext;
    private List<Step> mSteps;

    private final StepOnClickHandler mStepClickHandler;

    public StepAdapter(Context context, StepOnClickHandler clickHandler, List<Step> stepData) {
        mContext = context;
        mSteps = stepData;
        mStepClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public StepAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdStepListItem = R.layout.recipe_step_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdStepListItem, parent, false);

        return new StepAdapterViewHolder(view, mStepClickHandler, mSteps);
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapterViewHolder holder, int position) {
        holder.bind(mSteps.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (mSteps == null) {
            return 0;
        }
        return mSteps.size();
    }
}
