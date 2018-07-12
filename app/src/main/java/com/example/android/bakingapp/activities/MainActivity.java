package com.example.android.bakingapp.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.architectureComponents.RecipeViewModel;
import com.example.android.bakingapp.adapters.RecipeAdapter;
import com.example.android.bakingapp.interfaces.RecipeOnClickHandler;
import com.example.android.bakingapp.models.Recipe;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipeOnClickHandler {

    public final static String RECIPE_ENTITY = "recipe_entity";
    private RecipeAdapter mRecipeAdapter;
    private RecyclerView mRecipeRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipeRecyclerView = findViewById(R.id.recipes_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecipeRecyclerView.setLayoutManager(layoutManager);
        mRecipeRecyclerView.setHasFixedSize(true);

        mRecipeAdapter = new RecipeAdapter(this, this);

        mRecipeRecyclerView.setAdapter(mRecipeAdapter);

        setupViewModel();
    }

    private void setupViewModel() {
        RecipeViewModel viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        viewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                mRecipeAdapter.setRecipeData(recipes);
            }
        });

    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        Intent intentToStartRecipeActivity = new Intent(this, RecipeActivity.class);
        intentToStartRecipeActivity.putExtra(RECIPE_ENTITY, recipe);
        startActivity(intentToStartRecipeActivity);
    }
}
