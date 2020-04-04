package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OpinionDialogBox extends Dialog {
    private Opinion opinion;
    private TextView description;
    private TextView username;
    private ImageView profilePic;
    private RatingBar ratingBar;
    private RecyclerView gallery;
    private RecyclerView places;


    public OpinionDialogBox(@NonNull Context context,Opinion opinion) {
        super(context);
        this.opinion=opinion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opinion_dialog_box);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        findView();
    }

    private void findView() {
        description=findViewById(R.id.opinion_dialog_description);
        username= findViewById(R.id.opinion_dialog_username);
        profilePic = findViewById(R.id.opinion_dialog_profile_pic);
        ratingBar = findViewById(R.id.opinion_dialog_rating_bar);

            }
}
