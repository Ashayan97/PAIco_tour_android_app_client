package com.paico.paico_tour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageLoader extends AsyncTask<String, Void, Void> {
    private static ImageLoader imageLoader;

    public ImageView imageView;

    public static ImageLoader getInstance(ImageView imageView) {
        if (imageLoader == null)
            imageLoader = new ImageLoader();
        imageLoader.imageView=imageView;
        return imageLoader;
    }

    private ImageLoader() {
    }

    @Override
    protected Void doInBackground(String... strings) {
        Bitmap bmp = null;
        URL url = null;
        try {
            url = new URL(strings[0]);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imageView.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
