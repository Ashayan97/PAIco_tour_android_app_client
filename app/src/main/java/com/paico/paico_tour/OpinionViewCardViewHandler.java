package com.paico.paico_tour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OpinionViewCardViewHandler extends RecyclerView.Adapter<OpinionViewCardViewHandler.ViewHolder> {

    private ArrayList<Opinion> opinions;

    public OpinionViewCardViewHandler(ArrayList<Opinion> opinions) {
        this.opinions = opinions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_row_opinion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.description.setText(opinions.get(position).getDescription());
//        holder.profilePic.setImageBitmap(opinions.get(position).getProfilePic());
        holder.username.setText(opinions.get(position).getUsername());
        holder.ratingBar.setRating(opinions.get(position).getRate());
    }

    @Override
    public int getItemCount() {
        return opinions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView description;
        public ImageView profilePic;
        public RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.opinion_username);
            description = itemView.findViewById(R.id.opinion_user_description);
            profilePic = itemView.findViewById(R.id.opinion_profile_pic);
            ratingBar = itemView.findViewById(R.id.opinion_ratingBar);
        }
    }
}
