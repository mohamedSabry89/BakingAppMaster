package com.example.android.bakeandcake;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipeItemTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickRecipeNutellaPie() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.rv_reciep)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void clickRecipeBrownies() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.rv_reciep)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }

    @Test
    public void clickRecipeYellowCake() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.rv_reciep)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }

    @Test
    public void clickCheesecake() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.rv_reciep)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
    }
}
