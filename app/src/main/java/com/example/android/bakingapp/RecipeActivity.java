package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.bakingapp.models.Recipe;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intentThatStartedThisActivity = getIntent();
        if(intentThatStartedThisActivity != null){
            if(intentThatStartedThisActivity.hasExtra(MainActivity.RECIPE_ENTITY)){
                Recipe recipe = intentThatStartedThisActivity.getParcelableExtra(MainActivity.RECIPE_ENTITY);


                TextView textView = findViewById(R.id.recipe_name_text_view);
                textView.setText(recipe.getName());
            }
        }
    }
}
