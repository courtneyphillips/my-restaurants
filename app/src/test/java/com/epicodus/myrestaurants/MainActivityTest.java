package com.epicodus.myrestaurants;

import org.junit.Before;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RoboelectricGradleTestRunner.class)
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup() {
        activity = Roboelectric.setupActivity(MainActivity.class);
    }
}
