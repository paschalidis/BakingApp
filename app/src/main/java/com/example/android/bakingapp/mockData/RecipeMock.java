package com.example.android.bakingapp.mockData;

import android.support.annotation.VisibleForTesting;

import com.example.android.bakingapp.models.Ingredient;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.Step;

import java.util.ArrayList;

@VisibleForTesting
public class RecipeMock {

    public static Recipe getRecipe() {

        Recipe recipe = new Recipe(1, "Nutella Pie", getIngredients(),
                getSteps(), "8", "");

        return recipe;
    }

    public static ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        Ingredient ingredient = new Ingredient("2", "CUP", "Graham Cracker crumbs");
        ingredients.add(ingredient);

        ingredient = new Ingredient("6", "TBLSP", "unsalted butter, melted");
        ingredients.add(ingredient);

        ingredient = new Ingredient("0.5", "CUP", "granulated sugar");
        ingredients.add(ingredient);

        return ingredients;
    }

    public static ArrayList<Step> getSteps() {
        ArrayList<Step> steps = new ArrayList<>();

        Step step = new Step(0, "Recipe Introduction", "Recipe Introduction",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4",
                "");
        steps.add(step);

        step = new Step(1, "Starting prep", "1. Preheat the oven to 350°F. Butter a 9\" deep dish pie pan.",
                "",
                "");
        steps.add(step);

        step = new Step(2, "Prep the cookie crust.", "2. Whisk the graham cracker crumbs, 50 grams (1/4 cup) of sugar, and 1/2 teaspoon of salt together in a medium bowl. Pour the melted butter and 1 teaspoon of vanilla into the dry ingredients and stir together until evenly mixed.",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4",
                "");
        steps.add(step);

        return steps;
    }
}
