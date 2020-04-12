package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;

public class OpinionDialogBox extends Dialog {
    private Opinion opinion;
    private TextView description;
    private TextView username;
    private ImageView profilePic;
    private RatingBar ratingBar;
    private RecyclerView gallery;
    private RecyclerView places;
    private Button getPoint;


    public OpinionDialogBox(@NonNull Context context, Opinion opinion) {
        super(context);
        this.opinion = opinion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opinion_dialog_box);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        findView();
        setView();
    }

    private void setView() {
        description.setText(opinion.getDescription());
        username.setText(opinion.getUsername());
        ratingBar.setRating(opinion.getRate());
        //TODO set places and set gallery

        OpinionGalleryCardViewHandler galleryAdapter = new OpinionGalleryCardViewHandler(opinion.getGalleryUrls(), opinion.getPlacesGallery());
        gallery.setAdapter(galleryAdapter);
        gallery.setLayoutManager(new LinearLayoutManager(getContext()));

        OpinionListOfPlacesCardView opinionListOfPlacesCardView = new OpinionListOfPlacesCardView(getContext(), opinion.getPlaces());
        places.setAdapter(opinionListOfPlacesCardView);
        places.setLayoutManager(new LinearLayoutManager(getContext()));

        getPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPointDialogBox getPointDialogBox = new GetPointDialogBox(getContext(), new PointHandler() {
                    @Override
                    public void pointHandler(float point) {
                        //TODO
                    }
                });
                getPointDialogBox.show();
            }
        });
    }

    private void findView() {
        description = findViewById(R.id.opinion_dialog_description);
        username = findViewById(R.id.opinion_dialog_username);
        profilePic = findViewById(R.id.opinion_dialog_profile_pic);
        ratingBar = findViewById(R.id.opinion_dialog_rating_bar);
        gallery = findViewById(R.id.opinion_dialog_gallery);
        places = findViewById(R.id.opinion_dialog_list_of_place);
        getPoint = findViewById(R.id.opinion_dialog_get_point_button);

    }
}
