package com.epicodus.myrestaurants.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.myrestaurants.Constants;
import com.epicodus.myrestaurants.R;
import com.epicodus.myrestaurants.adapters.FirebaseRestaurantListAdapter;
import com.epicodus.myrestaurants.util.OnStartDragListener;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRestaurantListFragment extends BaseFragment implements OnStartDragListener {
    private Query mQuery;
    private Firebase mFirebaseRestaurantsRef;
    private FirebaseRestaurantListAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseRestaurantsRef = new Firebase(Constants.FIREBASE_URL_RESTAURANTS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_restaurant_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseQuery();
        setUpRecyclerView();
        return view;
    }

}
