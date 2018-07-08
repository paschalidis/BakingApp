package com.example.android.bakingapp.models;

import java.util.ArrayList;

public class Recipe {

    private int mId;
    private String mName;
    private ArrayList<Ingredient> mIngredients;
    private ArrayList<Step> mSteps;
    private int mServings;
    private String mImage;

    public Recipe(int id, String name, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, int servings, String image) {
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

    public void setId(String name) {
        this.mName = name;
    }

    public ArrayList<Ingredient> getmIngredients() {
        return this.mIngredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.mIngredients = ingredients;
    }

    public ArrayList<Step> getSteps() {
        return this.mSteps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.mSteps = steps;
    }

    public int getServings() {
        return this.mServings;
    }

    public void setServings(int servings) {
        this.mServings = servings;
    }

    public String getImage() {
        return this.mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }
}
