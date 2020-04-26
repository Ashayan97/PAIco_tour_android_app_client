package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class MarkerDialogBox extends Dialog {
    private Places places;
    private ImageView imageView;
    private ImageButton imageButton;
    private RatingBar ratingBar;
    private TextView name;
    private TextView address;
    private TextView phone;
    private ProgressBar progressBar;


    public MarkerDialogBox(@NonNull Context context,Places places) {
        super(context);
        this.places=places;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_show_dialog_box);
        getWindow().setBackgroundDrawableResource(R.drawable.window_dialog_background);
        findView();
        setView();

    }

    private void setView() {
        name.setText(places.getName());
        address.setText(places.getAddress());
        phone.setText(places.getPhoneNumber());
        new ImageLoader(imageView,progressBar).execute(places.getProfilePicUrl());
        ratingBar.setRating(places.getRate());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson=new Gson();
                String placeClass=gson.toJson(places);
                Intent intent=new Intent(getContext(),PlaceActivity.class);
                intent.putExtra("place",placeClass);
                getContext().startActivity(intent);
            }
        });
    }

    private void findView() {
        imageView = findViewById(R.id.place_show_dialog_box_image_view);
        imageButton = findViewById(R.id.place_show_dialog_box_show_btn);
        name = findViewById(R.id.place_show_dialog_box_name);
        address = findViewById(R.id.place_show_dialog_box_address);
        phone = findViewById(R.id.place_show_dialog_box_phone);
        progressBar = findViewById(R.id.place_show_dialog_box_progress_bar);
        ratingBar = findViewById(R.id.place_show_dialog_box_rate);
    }
}
