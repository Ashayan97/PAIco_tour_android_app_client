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

import com.paico.paico_tour.objects.Places;

public class OpinionGalleryCardViewHandler extends RecyclerView.Adapter<OpinionGalleryCardViewHandler.ViewHolder> {
    private String[] galleryUrls;
    private Places[] places;


    public OpinionGalleryCardViewHandler(String[] galleryUrls, Places[] places) {
        this.galleryUrls = galleryUrls;
        this.places = places;
        if (galleryUrls==null)
            this.galleryUrls=new String[0];
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_opinion_dialog_gallery, parent, false);
        return new OpinionGalleryCardViewHandler.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.placeName.setText(places[position].getName());
        new ImageLoader(holder.placeImage,holder.progressBar).execute(galleryUrls[position]);
        holder.point.setText(String.valueOf(places[position].getRate()));
        holder.ratingBar.setRating(places[position].getRate());
    }

    @Override
    public int getItemCount() {
        return galleryUrls.length;
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
