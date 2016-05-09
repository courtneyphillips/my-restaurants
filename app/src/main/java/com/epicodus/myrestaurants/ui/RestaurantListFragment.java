package com.epicodus.myrestaurants.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.myrestaurants.R;
import com.epicodus.myrestaurants.adapters.RestaurantListAdapter;
import com.epicodus.myrestaurants.models.Restaurant;

import java.util.ArrayList;

import butterknife.Bind;

public class RestaurantListFragment extends BaseFragment {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();
    private RestaurantListAdapter mAdapter;

}
