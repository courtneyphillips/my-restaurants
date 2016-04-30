package com.epicodus.myrestaurants.adapters;

import com.epicodus.myrestaurants.models.Restaurant;
import com.epicodus.myrestaurants.util.FirebaseRecyclerAdapter;
import com.firebase.client.Query;

public class FirebaseRestaurantListAdapter extends FirebaseRecyclerAdapter<RestaurantViewHolder, Restaurant> {
    public FirebaseRestaurantListAdapter(Query query, Class<Restaurant> itemClass) {
        super(query, itemClass);
    }
}
