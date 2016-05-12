package com.epicodus.myrestaurants.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.myrestaurants.Constants;
import com.epicodus.myrestaurants.R;
import com.epicodus.myrestaurants.models.Restaurant;
import com.epicodus.myrestaurants.ui.RestaurantDetailActivity;
import com.epicodus.myrestaurants.ui.RestaurantDetailFragment;
import com.epicodus.myrestaurants.ui.SavedRestaurantListActivity;
import com.epicodus.myrestaurants.util.ItemTouchHelperViewHolder;
import com.epicodus.myrestaurants.util.OnRestaurantSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ItemTouchHelperViewHolder {

    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    @Bind(R.id.restaurantImageView) ImageView mRestaurantImageView;
    @Bind(R.id.restaurantNameTextView) TextView mNameTextView;
    @Bind(R.id.categoryTextView) TextView mCategoryTextView;
    @Bind(R.id.ratingTextView) TextView mRatingTextView;

    private Context mContext;
    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    private int mOrientation;
    private Integer mPosition;
    private OnRestaurantSelectedListener mRestaurantSelectedListener;

    public RestaurantViewHolder(View itemView, ArrayList<Restaurant> restaurants, OnRestaurantSelectedListener restaurantSelectedListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mRestaurants = restaurants;
        mContext = itemView.getContext();
        mOrientation = itemView.getResources().getConfiguration().orientation;
        mRestaurantSelectedListener = restaurantSelectedListener;


        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
            createDetailFragment(0);
        }

        itemView.setOnClickListener(this);
    }

        private void createDetailFragment(int position){
        RestaurantDetailFragment detailFragment = RestaurantDetailFragment.newInstance(mRestaurants, position, mContext.getClass().getSimpleName());
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.restaurantDetailContainer, detailFragment);
        ft.commit();
    }

    public void bindRestaurant(Restaurant restaurant) {

        if (!restaurant.getImageUrl().contains("http")) {
            try {
                Bitmap image = decodeFromFirebaseBase64(restaurant.getImageUrl());
                mRestaurantImageView.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Picasso.with(mContext)
                    .load(restaurant.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mRestaurantImageView);
        }

        mNameTextView.setText(restaurant.getName());
        mCategoryTextView.setText(restaurant.getCategories().get(0));
        mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
    }

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }

    @Override
    public void onClick(View v) {
        mPosition = getLayoutPosition();
        mRestaurantSelectedListener.onRestaurantSelected(mPosition, mRestaurants);

        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createDetailFragment(mPosition);
        } else {
            Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
            intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
            intent.putExtra(Constants.EXTRA_KEY_RESTAURANTS, Parcels.wrap(mRestaurants));

            if (mContext.getClass().getSimpleName().equals(SavedRestaurantListActivity.class.getSimpleName())) {
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_SAVED);
            } else {
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FIND);
            }

            mContext.startActivity(intent);
        }
    }
}
