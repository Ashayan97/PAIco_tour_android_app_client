package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OpinionDialogGetOpinionBox extends Dialog {

    private RecyclerView galleryRecyclerView;
    private RecyclerView placesRecyclerView;
    private ImageButton submit;
    private ImageButton deny;
    private ImageButton addGalleryBtn;
    private ImageButton addPlaceBtn;
    private EditText descriptionEditText;


    public OpinionDialogGetOpinionBox(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opinion_add_new_opinion_dialog_box);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(R.drawable.window_dialog_background);
        findView();
        onClick();

    }

    private void onClick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        addGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        addPlaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        descriptionEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

    }

    private void findView() {
        galleryRecyclerView = findViewById(R.id.opinion_add_new_opinion_pic_recycler_view);
        placesRecyclerView = findViewById(R.id.opinion_dialog_get_opinion_get_place_recycler_view);
        submit = findViewById(R.id.opinion_dialog_get_opinion_submit_btn);
        deny = findViewById(R.id.opinion_dialog_get_opinion_deny_btn);
        addGalleryBtn = findViewById(R.id.opinion_dialog_get_opinion_get_pic_button);
        addPlaceBtn = findViewById(R.id.opinion_dialog_get_opinion_get_place_button);
        descriptionEditText = findViewById(R.id.opinion_add_new_opinion_get_description);

    }
}
