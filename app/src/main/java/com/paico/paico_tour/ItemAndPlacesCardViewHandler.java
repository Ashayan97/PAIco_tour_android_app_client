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

import com.paico.paico_tour.object_classes.Item;

import java.util.ArrayList;


public class ItemAndPlacesCardViewHandler extends RecyclerView.Adapter<ItemAndPlacesCardViewHandler.ViewHolder> {

    private ArrayList<Item> items;

    public ItemAndPlacesCardViewHandler(ArrayList<Item> items) {
        if (items == null)
            items = new ArrayList<>();
        else
            this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_and_places_temp_row, parent, false);
        return new ItemAndPlacesCardViewHandler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.placeName.setText(items.get(position).getTitle());
        holder.info.setText(items.get(position).getInfo());
        holder.point.setText(String.valueOf(items.get(position).getPoint()));
        holder.ratingBar.setRating(items.get(position).getPoint());
        new ImageLoader(holder.placeImage,holder.progressBar).doInBackground(items.get(position).getImgProfile());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView placeName;
        public TextView info;
        public TextView point;
        public ImageView placeImage;
        public RatingBar ratingBar;
        public ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.item_and_places_temp_row_progress_bar);
            placeName = itemView.findViewById(R.id.item_and_places_place_name);
            point = itemView.findViewById(R.id.item_and_places_place_point);
            placeImage = itemView.findViewById(R.id.item_and_places_image_view);
            ratingBar = itemView.findViewById(R.id.item_and_places_rating_bar);
            info = itemView.findViewById(R.id.item_and_places_place_description);

        }

    }
}
