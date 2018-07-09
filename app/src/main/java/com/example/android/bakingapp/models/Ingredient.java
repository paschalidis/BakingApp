package com.example.android.bakingapp.models;

import com.example.android.bakingapp.recipeApi.ApiUtilities;
import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName(ApiUtilities.INGREDIENT_QUANTITY)
    private String mQuantity;

    @SerializedName(ApiUtilities.INGREDIENT_MEASURE)
    private String mMeasure;

    @SerializedName(ApiUtilities.INGREDIENT_INGREDIENT)
    private String mIngredient;

    public Ingredient(String quantity, String measure, String ingredient) {
        this.mQuantity = quantity;
        this.mMeasure = measure;
        this.mIngredient = ingredient;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String quantity) {
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
