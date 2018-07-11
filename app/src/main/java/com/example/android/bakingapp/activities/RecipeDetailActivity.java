package com.example.android.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.fragments.RecipeFragment;
import com.example.android.bakingapp.models.Step;

public class RecipeDetailActivity extends AppCompatActivity {

    private Step mStep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(RecipeFragment.STEP_ENTITY)) {
                mStep = intentThatStartedThisActivity.getParcelableExtra(RecipeFragment.STEP_ENTITY);

                TextView textView = findViewById(R.id.recipe_detail_description_text_view);
                textView.setText(mStep.getDescription());
            }
        }
    }
}
