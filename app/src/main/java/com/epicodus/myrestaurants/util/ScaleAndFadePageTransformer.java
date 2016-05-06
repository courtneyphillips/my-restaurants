package com.epicodus.myrestaurants.util;

import android.support.v4.view.ViewPager;
import android.view.View;

public class ScaleAndFadePageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.9f;
    private static final float MIN_ALPHA = 0.7f;

    @Override
    public void transformPage(View page, float position) {
        float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));

        page.setScaleX(scaleFactor);
        page.setScaleY(scaleFactor);

        page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                / (1 - MIN_SCALE)
                * (1 - MIN_ALPHA));

    }
}
