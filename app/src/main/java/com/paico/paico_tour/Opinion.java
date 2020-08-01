package com.paico.paico_tour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.paico.paico_tour.object_classes.Places;

import java.io.InputStream;
import java.util.ArrayList;


public class Opinion {
    private String username;
    private String description;
    private float rate;
    private String profilePicAddress;
    private ArrayList<Places> places;
    private ArrayList<String> galleryUrls;
    private ArrayList<Places> placesGallery;


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

    public void setProfilePicAddress(String profilePicAddress) {
        this.profilePicAddress = profilePicAddress;
    }

    public ArrayList<Places> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Places> places) {
        this.places = places;
    }

    public ArrayList<String> getGalleryUrls() {
        return galleryUrls;
    }

    public void setGalleryUrls(ArrayList<String> galleryUrls) {
        this.galleryUrls = galleryUrls;
    }

    public ArrayList<Places> getPlacesGallery() {
        return placesGallery;
    }

    public void setPlacesGallery(ArrayList<Places> placesGallery) {
        this.placesGallery = placesGallery;
    }


}
