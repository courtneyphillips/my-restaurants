package com.epicodus.myrestaurants.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.myrestaurants.Constants;
import com.epicodus.myrestaurants.R;
import com.epicodus.myrestaurants.adapters.FirebaseRestaurantListAdapter;
import com.epicodus.myrestaurants.models.Restaurant;
import com.epicodus.myrestaurants.util.OnStartDragListener;
import com.epicodus.myrestaurants.util.SimpleItemTouchHelperCallback;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRestaurantListActivity extends AppCompatActivity implements OnStartDragListener {
//    private Query mQuery;
//    private Firebase mFirebaseRestaurantsRef;
//    private FirebaseRestaurantListAdapter mAdapter;
//    private SharedPreferences mSharedPreferences;
//    private ItemTouchHelper mItemTouchHelper;
//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mFirebaseRestaurantsRef = new Firebase(Constants.FIREBASE_URL_RESTAURANTS);

        setUpFirebaseQuery();
        setUpRecyclerView();
    }

    private void setUpFirebaseQuery() {
        String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
        String location = mFirebaseRestaurantsRef.child(userUid).toString();
        mQuery = new Firebase(location).orderByChild("index");    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseRestaurantListAdapter(mQuery, Restaurant.class, this);
        //In line below, we change 'this' to 'getActivity()' becasue fragments do not have own context:
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String uid = mSharedPreferences.getString(Constants.KEY_UID, null);
        for (Restaurant restaurant : mAdapter.getItems()) {
            String pushID = restaurant.getPushId();
            restaurant.setIndex(Integer.toString(mAdapter.getItems().indexOf(restaurant)));
            mFirebaseRestaurantsRef.child(uid)
                    .child(pushID)
                    .setValue(restaurant);
        }
    }
}