package com.epicodus.myrestaurants.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.myrestaurants.R;
import com.epicodus.myrestaurants.adapters.FirebaseRestaurantListAdapter;
import com.epicodus.myrestaurants.util.OnStartDragListener;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;

public class SavedRestaurantListFragment extends BaseFragment implements OnStartDragListener {
    private Query mQuery;
    private Firebase mFirebaseRestaurantsRef;
    private FirebaseRestaurantListAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

}
