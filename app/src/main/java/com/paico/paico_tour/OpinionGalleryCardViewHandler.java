package com.paico.paico_tour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paico.paico_tour.object_classes.Places;
import com.paico.paico_tour.object_classes.PlacesLoader;

import java.util.ArrayList;


public class OpinionGalleryCardViewHandler extends RecyclerView.Adapter<OpinionGalleryCardViewHandler.ViewHolder> {
    private ArrayList<String> galleryUrls;
    private ArrayList<Integer> places;


    public OpinionGalleryCardViewHandler(ArrayList<String> galleryUrls, ArrayList<Integer> places) {
        this.galleryUrls = galleryUrls;
        this.places = places;
        if (galleryUrls==null)
            this.galleryUrls=new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_opinion_dialog_gallery, parent, false);
        return new OpinionGalleryCardViewHandler.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.placeName.setText(PlacesLoader.getInstance().getPlacesArrayList().get(places.get(position)).getName());
        new ImageLoader(holder.placeImage,holder.progressBar).execute(galleryUrls.get(position));
        holder.point.setText(String.valueOf(PlacesLoader.getInstance().getPlacesArrayList().get(places.get(position)).getRate()));
        holder.ratingBar.setRating(PlacesLoader.getInstance().getPlacesArrayList().get(places.get(position)).getRate());
    }

    @Override
    public int getItemCount() {
        return galleryUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView placeName;
        public TextView point;
        public ImageView placeImage;
        public RatingBar ratingBar;
        public ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.opinion_dialog_gallery_progress_bar);
            placeName = itemView.findViewById(R.id.opinion_dialog_gallery_place_name);
            point = itemView.findViewById(R.id.opinion_dialog_gallery_rate_num);
            placeImage = itemView.findViewById(R.id.opinion_dialog_gallery_image_view);
            ratingBar = itemView.findViewById(R.id.opinion_dialog_gallery_rating_bar);
        }

    }
}
