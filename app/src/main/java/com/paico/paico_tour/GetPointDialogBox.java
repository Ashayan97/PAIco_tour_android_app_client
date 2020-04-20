package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.annotation.NonNull;



public class GetPointDialogBox extends Dialog {
    private PointHandler pointHandler;
    private RatingBar ratingBar;
    private Button submit;


    public GetPointDialogBox(@NonNull Context context, PointHandler pointHandler) {
        super(context);
        this.pointHandler=pointHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_point_dialog_box);
        findView();
        onClick();
    }

    private void onClick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointHandler.pointHandler(ratingBar.getRating());
            }
        });
    }

    private void findView() {
        ratingBar=findViewById(R.id.get_point_dialog_rating_bar);
        submit = findViewById(R.id.opinion_dialog_get_point_button);
    }
}
