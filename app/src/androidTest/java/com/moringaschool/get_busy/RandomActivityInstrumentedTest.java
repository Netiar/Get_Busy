package com.moringaschool.get_busy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.moringaschool.get_busy.ui.RandomActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RandomActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<RandomActivity> myRule = new ActivityScenarioRule<RandomActivity>(RandomActivity.class);

    @Test
    public void clickButtonFavourites(){
        onView(withId(R.id.like)).perform(click());
    }
}
