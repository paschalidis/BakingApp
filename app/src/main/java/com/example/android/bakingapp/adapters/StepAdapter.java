package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Step;

import java.util.ArrayList;
import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder> {

    private Context mContext;
    private List<Step> mSteps;
    private final StepAdapterOnClickHandler mStepClickHandler;

    public interface StepAdapterOnClickHandler {
        void onClick(Step step);
    }

    public StepAdapter(Context context, StepAdapterOnClickHandler clickHandler, List<Step> stepData) {
        mContext = context;
        mSteps = stepData;
        mStepClickHandler = clickHandler;
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mStepTextView;

        public StepAdapterViewHolder(View itemView) {
            super(itemView);
            mStepTextView = (TextView) itemView.findViewById(R.id.recipe_step_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mStepClickHandler.onClick(mSteps.get(adapterPosition));
        }

        private void bind(String recipeTitle) {
            mStepTextView.setText(recipeTitle);
        }
    }

    @NonNull
    @Override
    public StepAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdStepListItem = R.layout.recipe_step_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdStepListItem, parent, shouldAttachToParentImmediately);

        return new StepAdapter.StepAdapterViewHolder(view);
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

    public void clearData() {
        mSteps.clear();
        notifyDataSetChanged();
    }

    public List<Step> getStepData() {
        return mSteps;
    }
}
