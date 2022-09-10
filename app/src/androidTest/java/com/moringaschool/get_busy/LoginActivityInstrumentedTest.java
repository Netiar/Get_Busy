package com.moringaschool.get_busy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.endsWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.moringaschool.get_busy.ui.LoginActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LoginActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> myRule  = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    @Test
    public void addEditTextInputs(){
        onView(allOf(Matchers.allOf(isDescendantOfA(withId(R.id.userName)),withClassName(endsWith("TextInputEditText"))))).perform(typeText("Kuja wewe"));
        onView(allOf(Matchers.allOf(isDescendantOfA(withId(R.id.userPhone)),withClassName(endsWith("TextInputEditText"))))).perform(typeText("Kuja wewe"));
    }

    @Test
    public void clickSignUpButton(){
        onView(withId(R.id.btnLogin)).perform(click());
    }

    @Test
    public void clickLoginButton(){
        onView(withId(R.id.btnSign)).perform(click());
    }

    @Test
    public void clickRemCheckbox(){
        onView(withId(R.id.checkBox)).perform(click());
    }
}
