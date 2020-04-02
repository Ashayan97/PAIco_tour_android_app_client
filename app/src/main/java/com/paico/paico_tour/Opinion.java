package com.paico.paico_tour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;


public class Opinion {
    private String username;
    private String description;
    private int rate;
    private String profilePicAddress;

    public Opinion(String username, String description, int rate, String profilePicAddress) {
        this.username = username;
        this.description = description;
        this.rate = rate;
        this.profilePicAddress = profilePicAddress;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getProfilePicAddress() {
        return profilePicAddress;
    }

    public Bitmap getProfilePic() { ;
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
