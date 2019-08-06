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
public class NextRecipeButtonTest {

    @Rule public ActivityTestRule<MainActivity> recipeDetailFragmentActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResource;
    private String mRecipeName = "Nutella Pie";

    @Before
    public void registerIdlingResource() {
        mIdlingResource = recipeDetailFragmentActivityTestRule.getActivity().getIdlingResource();

        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void checkForDataInAdapter() {

        //find the adapter view and perform a click action
        onView(withId(R.id.main_recipe_list_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.change_btn)).perform(click());

        //check if the data is same as expected
        onView(withId(R.id.recipe_heading_tv)).check(matches(withText(mRecipeName)));

    }

    @After
    public void unregisterIdlingResource() {
        if(mIdlingResource != null){
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
