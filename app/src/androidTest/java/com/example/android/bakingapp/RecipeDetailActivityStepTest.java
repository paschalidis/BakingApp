package com.example.android.bakingapp;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.bakingapp.activities.RecipeActivity;
import com.example.android.bakingapp.activities.RecipeDetailActivity;
import com.example.android.bakingapp.mockData.RecipeMock;
import com.example.android.bakingapp.models.Recipe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityStepTest {


    private Recipe mRecipe;

    @Rule
    public ActivityTestRule<RecipeDetailActivity> mRecipeDetailActivityTestRule =
            new ActivityTestRule<>(RecipeDetailActivity.class, true, false);

    @Before
    public void initRecipeActivity() {
        mRecipe = RecipeMock.getRecipe();
    }

    @Test
    public void checkStepViews() {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.RECIPE_ENTITY, mRecipe);
        intent.putExtra(RecipeActivity.STEP_INDEX, 0);
        mRecipeDetailActivityTestRule.launchActivity(intent);

        // Check activity title
        String activityTitle = (String) mRecipeDetailActivityTestRule.getActivity().getTitle();
        onView(withText(activityTitle)).check(matches(withText(mRecipe.getName())));

        // Check step player
        onView(withId(R.id.step_player_view))
                .check(matches(isDisplayed()));

        // Check steps navigator
        onView(withId(R.id.step_navigation_container))
                .check(matches(isDisplayed()));

        // Check description
        onView(withId(R.id.recipe_detail_description_text_view))
                .check(matches(withText(mRecipe.getSteps().get(0).getDescription())));
    }
}
