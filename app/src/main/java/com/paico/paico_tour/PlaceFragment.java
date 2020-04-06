package com.paico.paico_tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceFragment extends Fragment {

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

    public PlaceFragment(Places place) {
        this.place = place;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.place_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        onClick();
        setView();
    }

    private void setView() {
//     TODO   videoView.setVideoPath(place.getVideoUrl());
        placeInfo.setText(getString(R.string.place_info_form,place.getName(),place.getHourTime(),place.getAdministration(),place.getPhoneNumber()));
        title.setText(place.getName());
        history.setText(place.getDescription());
        PlaceFragmentGalleryCardViewHandler cardViewHandler=new PlaceFragmentGalleryCardViewHandler(place.getImgUrls());
        gallery.setAdapter(cardViewHandler);
        gallery.setLayoutManager(new LinearLayoutManager(getContext()));
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

    private void findView(View view) {
        gallery = view.findViewById(R.id.place_fragment_gallery);
        videoView = view.findViewById(R.id.place_fragment_video_view);
        placeInfo = view.findViewById(R.id.place_fragment_info_form);
        history = view.findViewById(R.id.place_fragment_description);
        title = view.findViewById(R.id.place_fragment_title);
        ticket = view.findViewById(R.id.place_fragment_ticket);
        direction = view.findViewById(R.id.place_fragment_direction);
        liveInfo = view.findViewById(R.id.place_fragment_live_info);
        placesAndItem = view.findViewById(R.id.place_fragment_item_and_places);

    }

}
