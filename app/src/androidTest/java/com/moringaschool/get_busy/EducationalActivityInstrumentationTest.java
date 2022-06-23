package com.moringaschool.get_busy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.moringaschool.get_busy.ui.EducationalActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class EducationalActivityInstrumentationTest {
    @Rule
   public ActivityScenarioRule<EducationalActivity> myRule = new ActivityScenarioRule<EducationalActivity>(EducationalActivity.class);

    @Test
    public void checkHorizontalScrollViewVisibility(){

        onView(withId(R.id.scroll)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void checkRecyclerViewVisibility(){
        onView(withId(R.id.lvp)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void checkProgressBarVisibility(){
        onView(withId(R.id.progress)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void checkButtonVisibility(){
        onView(withId(R.id.submit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void performButtonClick(){
        onView(withId(R.id.submit)).perform(click());
    }
}
