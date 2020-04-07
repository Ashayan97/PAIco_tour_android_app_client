package com.paico.paico_tour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
    private static ImageLoader imageLoader;

    public static ImageLoader getInstance() {
        if (imageLoader == null)
            imageLoader = new ImageLoader();
        return imageLoader;
    }

    private ImageLoader() {
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bmp = null;
        URL url = null;
        try {
            url = new URL(strings[0]);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }

}
