package com.example.android.bakingapp;


import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.example.android.bakingapp.main.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class NameOfRecipeTest {

    @Rule public ActivityTestRule<MainActivity> recipeDetailActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mRecipeIdlingResource;

    private String mRecipeName = "Yellow Cake";

    @Before
    public void registerIdlingResource() {
        mRecipeIdlingResource = recipeDetailActivityTestRule.getActivity().getIdlingResource();

        Espresso.registerIdlingResources(mRecipeIdlingResource);
    }

    @Test
    public void checkForName_afterRecyclerViewClick() {

        onView(withId(R.id.main_recipe_list_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.recipe_heading_tv)).check(matches(withText(mRecipeName)));

    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(mRecipeIdlingResource);
    }

}
