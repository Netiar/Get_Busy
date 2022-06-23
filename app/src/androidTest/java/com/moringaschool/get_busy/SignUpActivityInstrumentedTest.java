package com.moringaschool.get_busy;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.moringaschool.get_busy.ui.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SignUpActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule<RegisterActivity> myRule = new ActivityScenarioRule<RegisterActivity>(RegisterActivity.class);

    @Test
    public void addInputInEditTexts(){
        onView(allOf(isDescendantOfA(withId(R.id.userName)),withClassName(endsWith("TextInputEditText")))).perform(typeText("Kuja wewe"));
        onView(allOf(isDescendantOfA(withId(R.id.userPhone)),withClassName(endsWith("TextInputEditText")))).perform(typeText("0000000000"));
        onView(allOf(isDescendantOfA(withId(R.id.userTicket)),withClassName(endsWith("TextInputEditText")))).perform(typeText("K@gmail.com"));
        onView(allOf(isDescendantOfA(withId(R.id.userSeat)),withClassName(endsWith("TextInputEditText")))).perform(typeText("Kuja wewe"));
        onView(allOf(isDescendantOfA(withId(R.id.userCoach)),withClassName(endsWith("TextInputEditText")))).perform(typeText("Kuja wewe"));
    }

    @Test
    public void performButtonClick(){
        onView(withId(R.id.btnSubmit)).perform(click());
    }
}
