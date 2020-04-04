package com.paico.paico_tour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;


public class Opinion {
    private String username;
    private String description;
    private float rate;
    private String profilePicAddress;
    private Places[] places;
    private String[] galleryUrls;
    private Places[] placesGallery;

    public Opinion(String username, String description, float rate, String profilePicAddress) {
        this.username = username;
        this.description = description;
        this.rate = rate;
        this.profilePicAddress = profilePicAddress;
    }


    public Places[] getPlacesGallery() {
        return placesGallery;
    }

    public void setPlacesGallery(Places[] placesGallery) {
        this.placesGallery = placesGallery;
    }

    public String[] getGalleryUrls() {
        return galleryUrls;
    }

    public void setGalleryUrls(String[] galleryUrls) {
        this.galleryUrls = galleryUrls;
    }

    public Places[] getPlaces() {
        return places;
    }

    public void setPlaces(Places[] places) {
        this.places = places;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getProfilePicAddress() {
        return profilePicAddress;
    }

    public Bitmap getProfilePic() {
        ;
        String urldisplay = profilePicAddress;
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;

    }

    public void setProfilePicAddress(String profilePicAddress) {
        this.profilePicAddress = profilePicAddress;
    }

}
