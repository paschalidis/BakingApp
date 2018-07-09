package com.example.android.bakingapp.models;

import com.example.android.bakingapp.recipeApi.ApiUtilities;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {

    @SerializedName(ApiUtilities.RECIPE_ID)
    private int mId;

    @SerializedName(ApiUtilities.RECIPE_NAME)
    private String mName;

    @SerializedName(ApiUtilities.RECIPE_INGREDIENTS)
    private List<Ingredient> mIngredients;

    @SerializedName(ApiUtilities.RECIPE_STEPS)
    private List<Step> mSteps;

    @SerializedName(ApiUtilities.RECIPE_SERVINGS)
    private String mServings;

    @SerializedName(ApiUtilities.RECIPE_IMAGE)
    private String mImage;

    public Recipe(int id, String name, List<Ingredient> ingredients, List<Step> steps, String servings, String image) {
        this.mId = id;
        this.mName = name;
        this.mIngredients = ingredients;
        this.mSteps = steps;
        this.mServings = servings;
        this.mImage = image;
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public List<Ingredient> getIngredients() {
        return this.mIngredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.mIngredients = ingredients;
    }

    public List<Step> getSteps() {
        return this.mSteps;
    }

    public void setSteps(List<Step> steps) {
        this.mSteps = steps;
    }

    public String getServings() {
        return this.mServings;
    }

    public void setServings(String servings) {
        this.mServings = servings;
    }

    public String getImage() {
        return this.mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }
}
