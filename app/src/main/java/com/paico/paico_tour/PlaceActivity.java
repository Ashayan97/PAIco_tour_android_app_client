package com.paico.paico_tour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

public class PlaceActivity extends AppCompatActivity {

    private Places place;

    private RecyclerView gallery;
    private VideoView videoView;
    private TextView placeInfo;
    private TextView history;
    private TextView title;
    private Button ticket;
    private Button direction;
    private Button liveInfo;
    private Button placesAndItem;
    private Button givePoint;
    private ScrollView scrollView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        Gson gson = new Gson();
        place = gson.fromJson(getIntent().getStringExtra("place"), Places.class);
        findView();
        setView();
        onClick();
    }

    private void setView() {
        scrollView.smoothScrollTo(0,0);
        videoView.setVideoPath(place.getVideoUrl());
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
        placeInfo.setText(getString(R.string.place_info_form, place.getName(), place.getHourTime(), place.getAdministration(), place.getPhoneNumber()));
        title.setText(place.getName());
        history.setText(place.getDescription());
        PlaceFragmentGalleryCardViewHandler cardViewHandler = new PlaceFragmentGalleryCardViewHandler(place.getImgUrls());
        gallery.setAdapter(cardViewHandler);
        gallery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
    }

    private void onClick() {
        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        liveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        placesAndItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PlaceActivity.this,ItemAndPlaceActivity.class);
                startActivity(intent);
            }
        });
        givePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPointDialogBox getPointDialogBox = new GetPointDialogBox(PlaceActivity.this, new PointHandler() {
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
        gallery = findViewById(R.id.place_fragment_gallery);
        videoView = findViewById(R.id.place_fragment_video_view);
        placeInfo = findViewById(R.id.place_fragment_info_form);
        history = findViewById(R.id.place_fragment_description);
        title = findViewById(R.id.place_fragment_title);
        ticket = findViewById(R.id.place_fragment_ticket);
        direction = findViewById(R.id.place_fragment_direction);
        liveInfo = findViewById(R.id.place_fragment_live_info);
        givePoint = findViewById(R.id.place_fragment_give_point);
        placesAndItem = findViewById(R.id.place_fragment_item_and_places);
        scrollView = findViewById(R.id.place_fragment_scroll_view);

    }

}
