package com.epicodus.myrestaurants;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;

/**
 * Created by staff on 4/19/16.
 */
public class MainActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);
}
