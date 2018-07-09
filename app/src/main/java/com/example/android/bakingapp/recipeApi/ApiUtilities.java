package com.example.android.bakingapp.recipeApi;

public class ApiUtilities {

    public static final String API_BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    public static final String API_RECIPES_ENDPOINT = "baking.json";

    /**
     * Recipe Entity
     */
    public static final String RECIPE_ID = "id";
    public static final String RECIPE_NAME = "name";
    public static final String RECIPE_INGREDIENTS = "ingredients";
    public static final String RECIPE_STEPS = "steps";
    public static final String RECIPE_SERVINGS = "servings";
    public static final String RECIPE_IMAGE = "image";

    /**
     * Ingredient Entity
     */
    public static final String INGREDIENT_QUANTITY = "quantity";
    public static final String INGREDIENT_MEASURE = "measure";
    public static final String INGREDIENT_INGREDIENT = "ingredient";

    /**
     * Step Entity
     */
    public static final String STEP_ID = "id";
    public static final String STEP_SHORT_DESCRIPTION = "shortDescription";
    public static final String STEP_DESCRIPTION = "description";
    public static final String STEP_VIDEO_URL = "videoURL";
    public static final String STEP_THUMBNAIL_URL = "thumbnailURL";

}
