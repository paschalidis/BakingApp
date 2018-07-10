package com.example.android.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.bakingapp.recipeApi.ApiUtilities;
import com.google.gson.annotations.SerializedName;

public class Ingredient implements Parcelable {

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

    private Ingredient(Parcel in) {
        mQuantity = in.readString();
        mMeasure = in.readString();
        mIngredient = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuantity);
        dest.writeString(mMeasure);
        dest.writeString(mIngredient);
    }

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int i) {
            return new Ingredient[i];
        }
    };
}
