package com.example.android.bakingapp.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.architectureComponents.RecipeViewModel;
import com.example.android.bakingapp.adapters.RecipeAdapter;
import com.example.android.bakingapp.clickHandlers.RecipeOnClickHandler;
import com.example.android.bakingapp.models.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecipeOnClickHandler {

    public final static String RECIPE_ENTITY = "recipe_entity";
    private final static int PORTRAIT_TABLET_COLUMNS = 2;
    private final static int LANDSCAPE_TABLET_COLUMNS = 3;
    private RecipeAdapter mRecipeAdapter;

    @BindView(R.id.recipes_recycler_view)
    RecyclerView mRecipeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        //on tablet
        if (findViewById(R.id.main_activity_tablet_layout) != null) {
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, PORTRAIT_TABLET_COLUMNS);
                mRecipeRecyclerView.setLayoutManager(gridLayoutManager);
            } else {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, LANDSCAPE_TABLET_COLUMNS);
                mRecipeRecyclerView.setLayoutManager(gridLayoutManager);
            }
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRecipeRecyclerView.setLayoutManager(layoutManager);
        }
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
