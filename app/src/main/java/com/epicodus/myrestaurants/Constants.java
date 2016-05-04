package com.epicodus.myrestaurants;

public class Constants {


    //Constants for Yelp API usage:
    public static final String YELP_CONSUMER_KEY = BuildConfig.YELP_CONSUMER_KEY;
    public static final String YELP_CONSUMER_SECRET = BuildConfig.YELP_CONSUMER_SECRET;
    public static final String YELP_TOKEN = BuildConfig.YELP_TOKEN;
    public static final String YELP_TOKEN_SECRET = BuildConfig.YELP_TOKEN_SECRET;
    public static final String YELP_BASE_URL = "https://api.yelp.com/v2/search?term=food";
    public static final String YELP_LOCATION_QUERY_PARAMETER = "location";
    public static final String PREFERENCES_LOCATION_KEY = "location";

    //Constants for general Firebase usage:
    public static final String FIREBASE_URL = BuildConfig.FIREBASE_ROOT_URL;

    //Constants used to save zip codes users search:
    public static final String FIREBASE_LOCATION_SEARCHED_LOCATION = "searchedLocation";
    public static final String FIREBASE_URL_SEARCHED_LOCATION = FIREBASE_URL + "/" + FIREBASE_LOCATION_SEARCHED_LOCATION;

    //Constants used when creating and saving Restaurant objects to Firebase:
    public static final String FIREBASE_LOCATION_RESTAURANTS = "restaurants";
    public static final String FIREBASE_URL_RESTAURANTS = FIREBASE_URL + "/" + FIREBASE_LOCATION_RESTAURANTS;

    //Constants used when creating and saving User objects to Firebase:
    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USERS;
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String KEY_UID = "UID";


}
