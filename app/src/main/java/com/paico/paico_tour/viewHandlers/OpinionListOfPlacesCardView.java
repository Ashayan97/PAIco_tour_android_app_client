package com.paico.paico_tour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

public class OpinionListOfPlacesCardView extends RecyclerView.Adapter<OpinionListOfPlacesCardView.ViewHolder> {

    private Places[] places;
    private Context context;

    public OpinionListOfPlacesCardView(Context context,Places[] places) {
        this.context=context;
        this.places = places;
        if (places==null)
            this.places=new Places[0];
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.opinion_dialog_list_of_places, parent, false);
        return new OpinionListOfPlacesCardView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.placeName.setText(places[position].getName());
        holder.description.setText(places[position].getDescription());
        holder.point.setText(String.valueOf(places[position].getRate()));
        holder.ratingBar.setRating(places[position].getRate());
        new ImageLoader(holder.placeImage,null).execute(places[position].getProfilePicUrl());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson=new Gson();
                String placeClass=gson.toJson(places[position]);
                Intent intent=new Intent(context,PlaceActivity.class);
                intent.putExtra("place",placeClass);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return places.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView placeName;
        public TextView point;
        public ImageView placeImage;
        public RatingBar ratingBar;
        public TextView description;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.opinion_dialog_places_layout);
            placeName = itemView.findViewById(R.id.opinion_dialog_places_place_name);
            point = itemView.findViewById(R.id.opinion_dialog_places_place_point);
            placeImage = itemView.findViewById(R.id.opinion_dialog_places_profile_pic);
            description = itemView.findViewById(R.id.opinion_dialog_places_place_description);
            ratingBar = itemView.findViewById(R.id.opinion_dialog_places_rating_bar);
        }

    }
}
