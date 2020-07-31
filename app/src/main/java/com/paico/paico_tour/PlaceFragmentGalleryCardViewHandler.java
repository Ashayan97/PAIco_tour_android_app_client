package com.paico.paico_tour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlaceFragmentGalleryCardViewHandler extends RecyclerView.Adapter<PlaceFragmentGalleryCardViewHandler.ViewHolder> {

    private ArrayList<String> imgUrls;

    public PlaceFragmentGalleryCardViewHandler(ArrayList<String> imgUrls) {
        this.imgUrls = imgUrls;
        if (imgUrls == null)
            this.imgUrls = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_gallery, parent, false);
        return new PlaceFragmentGalleryCardViewHandler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        new ImageLoader(holder.imageView, holder.progressBar).execute(imgUrls.get(position));
        holder.textView.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return imgUrls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;
        public ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.temp_gallery_image_view);
            textView = itemView.findViewById(R.id.temp_gallery_text_view_position);
            progressBar = itemView.findViewById(R.id.temp_gallery_progress_bar);
        }
    }
}
