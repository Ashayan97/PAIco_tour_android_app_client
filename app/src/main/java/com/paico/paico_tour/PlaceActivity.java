package com.paico.paico_tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    public void setPlace(Places place) {
        this.place = place;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        findView();
        setView();
        onClick();
    }

    private void setView() {
        videoView.setVideoPath(place.getVideoUrl());
        placeInfo.setText(getString(R.string.place_info_form,place.getName(),place.getHourTime(),place.getAdministration(),place.getPhoneNumber()));
        title.setText(place.getName());
        history.setText(place.getDescription());
        PlaceFragmentGalleryCardViewHandler cardViewHandler=new PlaceFragmentGalleryCardViewHandler(place.getImgUrls());
        gallery.setAdapter(cardViewHandler);
        gallery.setLayoutManager(new LinearLayoutManager(this));
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
                //TODO
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
        placesAndItem = findViewById(R.id.place_fragment_item_and_places);

    }

}
