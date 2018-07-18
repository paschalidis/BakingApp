package com.example.android.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.bakingapp.recipeApi.ApiUtilities;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    @SerializedName(ApiUtilities.RECIPE_ID)
    private int mId;

    @SerializedName(ApiUtilities.RECIPE_NAME)
    private String mName;

    @SerializedName(ApiUtilities.RECIPE_INGREDIENTS)
    private ArrayList<Ingredient> mIngredients;

    @SerializedName(ApiUtilities.RECIPE_STEPS)
    private ArrayList<Step> mSteps;

    @SerializedName(ApiUtilities.RECIPE_SERVINGS)
    private String mServings;

    @SerializedName(ApiUtilities.RECIPE_IMAGE)
    private String mImage;

    public Recipe() {
        this.mId = 0;
        this.mName = "";
        this.mIngredients = new ArrayList<>();
        this.mSteps = new ArrayList<>();
        this.mServings = "";
        this.mImage = "";
    }

    public Recipe(int id, String name, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, String servings, String image) {
        this.mId = id;
        this.mName = name;
        this.mIngredients = ingredients;
        this.mSteps = steps;
        this.mServings = servings;
        this.mImage = image;
    }

    private Recipe(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        if (mIngredients == null) {
            mIngredients = new ArrayList<>();
        }
        in.readTypedList(mIngredients, Ingredient.CREATOR);
        if (mSteps == null) {
            mSteps = new ArrayList<>();
        }
        in.readTypedList(mSteps, Step.CREATOR);
        mServings = in.readString();
        mImage = in.readString();
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

    public ArrayList<Ingredient> getIngredients() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeTypedList(mIngredients);
        dest.writeTypedList(mSteps);
        dest.writeString(mServings);
        dest.writeString(mImage);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int i) {
            return new Recipe[i];
        }
    };
}
