package com.paico.paico_tour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceFragmentGalleryCardViewHandler extends RecyclerView.Adapter<PlaceFragmentGalleryCardViewHandler.ViewHolder> {

    private String[] imgUrls;

    public PlaceFragmentGalleryCardViewHandler(String[] imgUrls) {
        this.imgUrls = imgUrls;
        if (imgUrls == null)
            this.imgUrls = new String[0];
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_temp, parent, false);
        return new PlaceFragmentGalleryCardViewHandler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageBitmap(ImageLoader.getInstance().doInBackground(imgUrls[position]));
    }

    @Override
    public int getItemCount() {
        return imgUrls.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.temp_gallery_image_view);
        }
    }
}
