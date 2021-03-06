package com.example.android.dickssportinggoods.module.details;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.dickssportinggoods.R;
import com.example.android.dickssportinggoods.base.ParentActivity;
import com.example.android.dickssportinggoods.module.home.adapter.PhotosAdapter;
import com.example.android.dickssportinggoods.mvp.Model.Venue;

import butterknife.BindView;



public class DetailsActivity extends ParentActivity {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.rating)
    TextView rating;

    @BindView(R.id.layout2)
    RecyclerView photos;
    @BindView(R.id.favorite)
    ImageButton favBtn;
    boolean isFavorite;

    @Override
    protected int getContentView() {
        return R.layout.activity_detail;
    }

    Venue venue;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showBackArrow();

        venue = (Venue) intent.getSerializableExtra("venue");
        setTitle("Venue Details");

        name.setText(venue.getVenue().getName());
        address.setText(venue.getVenue().getLocation().getAddress() + ", " + venue.getVenue().getLocation().getCity()
                + ", " + venue.getVenue().getLocation().getState() + ", " + venue.getVenue().getLocation().getCountry() + ", " + venue.getVenue().getLocation().getPostalCode());
        rating.setText("Rating: " + venue.getVenue().getRating());

        PhotosAdapter photosAdapter = new PhotosAdapter(DetailsActivity.this, venue.getVenue().getPhotos());
        photos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        photos.setAdapter(photosAdapter);
        checkFavouriteOnIntent();
        checkFavoriteOnRotation(savedInstanceState);
    }


    private void checkFavouriteOnIntent() {
        if (venue.isFavourite()) {
            favBtn.setImageResource(R.drawable.on_star);
            isFavorite = true;
        } else {
            favBtn.setImageResource(R.drawable.off_star);
            isFavorite = false;
        }
    }

    private void checkFavoriteOnRotation(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("state") == true) {
                favBtn.setImageResource(R.drawable.on_star);
                isFavorite = true;
            } else {
                favBtn.setImageResource(R.drawable.off_star);
                isFavorite = false;
            }
        }
    }

    public void onToggleStar(View view) {

        if (isFavorite) {
            favBtn.setImageResource(R.drawable.off_star);
            isFavorite = false;
        } else {
            favBtn.setImageResource(R.drawable.on_star);
            isFavorite = true;
        }
        //updateFavourites();
    }
//    public void updateFavourites(){
//        if (isFavorite) {
//            for (int i = 0; i < VenueListResponse.getInstance().getVenueListWithDistance().size(); i++) {
//                //System.out.println("id "+v.getVenue().getId());
//                //System.out.println("id2"+VenueListResponse.getInstance().getVenueListWithDistance().get(i).getVenue().getId());
//                if (v.getVenue().getId().equals(VenueListResponse.getInstance().getVenueListWithDistance().get(i).getVenue().getId())) {
//                    VenueListResponse.getInstance().getVenueListWithDistance().get(i).setFavourite(true);
//                    v.setFavourite(true);
//
//                }
//
//            }
//
//        }else{
//            for (int i = 0; i < VenueListResponse.getInstance().getVenueListWithDistance().size(); i++) {
//
//                VenueListResponse.getInstance().getVenueListWithDistance().get(i).setFavourite(false);
//                v.setFavourite(false);
//                //System.out.println("id3"+VenueListResponse.getInstance().getVenueListWithDistance().get(i).isFavourite());
//
//            }
//        }
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("onSaveInstanceState: " + isFavorite);
        outState.putBoolean("state", isFavorite);

    }
}
