package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.security.PrivateKey;

public class MarkerDialogBox extends Dialog {
    private Places places;
    private ImageView imageView;
    private ImageButton imageButton;
    private TextView name;
    private TextView address;
    private TextView phone;
    private ProgressBar progressBar;


    public MarkerDialogBox(@NonNull Context context,Places places) {
        super(context);
        this.places=places;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_show_dialog_box);

        findView();
        setView();

    }

    private void setView() {

    }

    private void findView() {
        imageView = findViewById(R.id.place_show_dialog_box_image_view);
        imageButton = findViewById(R.id.place_show_dialog_box_show_btn);
        name = findViewById(R.id.place_show_dialog_box_name);
        address = findViewById(R.id.place_show_dialog_box_address);
        phone = findViewById(R.id.place_show_dialog_box_phone);
        progressBar = findViewById(R.id.place_show_dialog_box_progress_bar);
    }
}
