package com.ardev.assessment.fyberchallenge;

import android.app.Application;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DataFormTest {


    @Rule
    public ActivityTestRule<DataFormActivity> mActivityRule = new ActivityTestRule(DataFormActivity.class);

    @Test
    public void checkFyberCall() {
        onView(withId(R.id.appIdEditTxt)).perform(typeText("2070"));
        onView(withId(R.id.userIdEditTxt)).perform(typeText("spiderman"));
        onView(withId(R.id.apiKeyEditTxt)).perform(typeText("1c915e3b5d42d05136185030892fbb846c278927"));
        onView(withId(R.id.connectBtn)).perform(click());
        onView(withText("Good Response")).check(matches(isDisplayed()));
    }

}//end DataFormTest