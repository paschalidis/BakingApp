package com.example.android.bakingapp;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.bakingapp.activities.MainActivity;
import com.example.android.bakingapp.activities.RecipeActivity;
import com.example.android.bakingapp.mockData.RecipeMock;
import com.example.android.bakingapp.models.Recipe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {

    private Recipe mRecipe;

    @Rule
    public ActivityTestRule<RecipeActivity> mRecipeActivityTestRule =
            new ActivityTestRule<>(RecipeActivity.class, true, false);

    @Before
    public void initRecipeActivity() {
        mRecipe = RecipeMock.getRecipe();

        Intent intent = new Intent();
        intent.putExtra(MainActivity.RECIPE_ENTITY, mRecipe);
        mRecipeActivityTestRule.launchActivity(intent);
    }

    @Test
    public void checkActivityViews() {

        // Check activity title
        String activityTitle = (String) mRecipeActivityTestRule.getActivity().getTitle();
        onView(withText(activityTitle)).check(matches(withText(mRecipe.getName())));

        // Check ingredient text view
        onView(withId(R.id.recipe_ingredients_text_view))
                .check(matches(isDisplayed()));

        // Check steps recycler view
        onView(withId(R.id.recipe_steps_recycler_view))
                .check(matches(isDisplayed()));

        // Check step item on recycler view
        onView(withId(R.id.recipe_steps_recycler_view))
                .check(matches(hasDescendant(withText(mRecipe.getSteps().get(0).getShortDescription()))));
    }

}
