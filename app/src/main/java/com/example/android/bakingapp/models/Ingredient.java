package com.example.android.bakingapp.models;

public class Ingredient {

    private int mQuantity;
    private String mMeasure;
    private String mIngredient;

    public Ingredient(int quantity, String measure, String ingredient) {
        this.mQuantity = quantity;
        this.mMeasure = measure;
        this.mIngredient = ingredient;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = quantity;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public void setMeasure(String measure) {
        this.mMeasure = measure;
    }

    public String getIngredient() {
        return mIngredient;
    }

    public void setIngredient(String ingredient) {
        this.mIngredient = ingredient;
    }

}
