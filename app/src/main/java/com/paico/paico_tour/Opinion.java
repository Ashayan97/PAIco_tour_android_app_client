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
    private int rateNumber;
    private String profilePicAddress;
    private ArrayList<Integer> places;
    private ArrayList<String> galleryUrls;
    private ArrayList<Integer> placesGallery;

    public ArrayList<Integer> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Integer> places) {
        this.places = places;
    }

    public ArrayList<Integer> getPlacesGallery() {
        return placesGallery;
    }

    public void setPlacesGallery(ArrayList<Integer> placesGallery) {
        this.placesGallery = placesGallery;
    }


    public int getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(int rateNumber) {
        this.rateNumber = rateNumber;
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

    public void setProfilePicAddress(String profilePicAddress) {
        this.profilePicAddress = profilePicAddress;
    }

    public ArrayList<String> getGalleryUrls() {
        return galleryUrls;
    }

    public void setGalleryUrls(ArrayList<String> galleryUrls) {
        this.galleryUrls = galleryUrls;
    }


}
