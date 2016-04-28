package com.epicodus.myrestaurants;

import android.app.Application;

import com.firebase.client.Firebase;

public class MyRestaurantsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
