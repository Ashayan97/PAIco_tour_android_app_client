package com.paico.paico_tour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FullScreenGalleryCardViewHandler extends RecyclerView.Adapter<FullScreenGalleryCardViewHandler.ViewHolder> {

    private String[] urls;

    public FullScreenGalleryCardViewHandler(String[] urls) {
        if (urls==null)
            this.urls=new String[0];
        else
            this.urls = urls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_full_screen_gallery, parent, false);

        return new  FullScreenGalleryCardViewHandler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return urls.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            progressBar=itemView.findViewById(R.id.item_and_places_)V
        }
    }
}
