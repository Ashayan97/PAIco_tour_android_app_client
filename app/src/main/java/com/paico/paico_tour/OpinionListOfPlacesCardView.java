package com.paico.paico_tour;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.paico.paico_tour.object_classes.Places;
import com.paico.paico_tour.object_classes.PlacesLoader;

import java.util.ArrayList;

public class OpinionListOfPlacesCardView extends RecyclerView.Adapter<OpinionListOfPlacesCardView.ViewHolder> {

    private ArrayList<Integer> places;
    private Context context;

    public OpinionListOfPlacesCardView(Context context,ArrayList<Integer> places) {
        this.context=context;
        this.places = places;
        if (places==null)
            this.places=new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.opinion_dialog_list_of_places, parent, false);
        return new OpinionListOfPlacesCardView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.placeName.setText(PlacesLoader.getInstance().getPlacesArrayList().get(places.get(position)).getName());
        holder.description.setText(PlacesLoader.getInstance().getPlacesArrayList().get(places.get(position)).getDescription());
        holder.point.setText(String.valueOf(PlacesLoader.getInstance().getPlacesArrayList().get(places.get(position)).getRate()));
        holder.ratingBar.setRating(PlacesLoader.getInstance().getPlacesArrayList().get(places.get(position)).getRate());
        new ImageLoader(holder.placeImage,null).execute(PlacesLoader.getInstance().getPlacesArrayList().get(places.get(position)).getProfilePicUrl());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson=new Gson();
                String placeClass=gson.toJson(PlacesLoader.getInstance().getPlacesArrayList().get(places.get(position)));
                Intent intent=new Intent(context,PlaceActivity.class);
                intent.putExtra("place",placeClass);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return places.size();
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
