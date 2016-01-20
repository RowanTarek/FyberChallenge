package com.ardev.assessment.fyberchallenge;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.util.regex.Pattern.matches;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ConnectToFyberUITest {

    @Rule
    public ActivityTestRule<DataFormActivity> mActivityRule = new ActivityTestRule<>(
            DataFormActivity.class);


    @Test
    public void connectToFyberFormTest() {

        onView(withId(R.id.appIdEditTxt)).perform(typeText("2070"));
        //for checking non empty strings
//        onView(withId(R.id.appIdEditTxt)).check(!matches(".*\\w.*"));

        onView(withId(R.id.userIdEditTxt)).perform(typeText("spiderman"));
        onView(withId(R.id.apiKeyEditTxt)).perform(typeText("1c915e3b5d42d05136185030892fbb846c278927"), closeSoftKeyboard());
        onView(withId(R.id.connectBtn)).perform(click());

        // at this point, another activity FyberOffersWall is started
//        onView(/*withText("Earn VCS Coins")*/withId(R.id.customPanel)).check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.connectBtn)).check(ViewAssertions.doesNotExist());
        //check fyber ad wall is on screen
    }
}