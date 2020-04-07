package com.paico.paico_tour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OpinionViewCardViewHandler extends RecyclerView.Adapter<OpinionViewCardViewHandler.ViewHolder> {

    private Context context;
    private ArrayList<Opinion> opinions;

    public OpinionViewCardViewHandler(ArrayList<Opinion> opinions, Context context) {
        this.opinions = opinions;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_row_opinion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.description.setText(opinions.get(position).getDescription());
        holder.profilePic.setImageBitmap(ImageLoader.getInstance().doInBackground(opinions.get(position).getProfilePicAddress()));
        holder.username.setText(opinions.get(position).getUsername());
        holder.ratingBar.setRating(opinions.get(position).getRate());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpinionDialogBox confrmationCodeDialogBox = new OpinionDialogBox(context,opinions.get(position));
                confrmationCodeDialogBox.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return opinions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView description;
        public ImageView profilePic;
        public LinearLayout linearLayout;
        public RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.card_view_layout);
            username = itemView.findViewById(R.id.opinion_username);
            description = itemView.findViewById(R.id.opinion_user_description);
            profilePic = itemView.findViewById(R.id.opinion_profile_pic);
            ratingBar = itemView.findViewById(R.id.opinion_ratingBar);
            linearLayout.setClickable(true);
        }

    }
}
