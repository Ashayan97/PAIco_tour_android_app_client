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
import java.util.zip.Inflater;

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
        holder.

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
            progressBar = itemView.findViewById(R.id.item_and_places_place_description);
            placeName = itemView.findViewById(R.id.item_and_places_place_description);
            point = itemView.findViewById(R.id.item_and_places_place_description);
            placeImage = itemView.findViewById(R.id.item_and_places_place_description);
            ratingBar = itemView.findViewById(R.id.item_and_places_place_description);
            info = itemView.findViewById(R.id.item_and_places_place_description);

        }

    }
}
