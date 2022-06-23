package com.moringaschool.get_busy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.moringaschool.get_busy.ui.FavouritesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FavouritesActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule<FavouritesActivity> myRule = new ActivityScenarioRule<FavouritesActivity>(FavouritesActivity.class);

    @Test
    public void checkRecyclerViewVisibility(){
        onView(withId(R.id.savedRecView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }


}
