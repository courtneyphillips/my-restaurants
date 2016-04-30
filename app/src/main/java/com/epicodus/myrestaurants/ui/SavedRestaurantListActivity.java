package com.epicodus.myrestaurants.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.myrestaurants.Constants;
import com.epicodus.myrestaurants.R;
import com.epicodus.myrestaurants.adapters.FirebaseRestaurantListAdapter;
import com.epicodus.myrestaurants.models.Restaurant;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRestaurantListActivity extends AppCompatActivity {
    private Query mQuery;
    private Firebase mFirebaseRestaurantsRef;
    private FirebaseRestaurantListAdapter mAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        mFirebaseRestaurantsRef = new Firebase(Constants.FIREBASE_URL_RESTAURANTS);

        setUpFirebaseQuery();
        setUpRecyclerView();
    }

    private void setUpFirebaseQuery() {
        String location = mFirebaseRestaurantsRef.toString();
        mQuery = new Firebase(location);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseRestaurantListAdapter(mQuery, Restaurant.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}