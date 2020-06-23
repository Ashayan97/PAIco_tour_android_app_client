package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paico.paico_tour.object_classes.Item;

public class ItemAndPlacesDialogBox extends Dialog {
    private Item item;
    private RecyclerView gallery;
    private Button getPointBtn;
    private RatingBar pointRatingBar;
    private TextView point;
    private TextView title;
    private TextView info;


    public ItemAndPlacesDialogBox(@NonNull Context context, Item item) {
        super(context);

        this.item=item;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_and_place_info_show);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(R.drawable.window_dialog_background);
        findView();
        setUpView(getContext());
        onClick();
    }

    private void onClick() {
        getPointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPointDialogBox getPointDialogBox = new GetPointDialogBox(getContext(), new PointHandler() {
                    @Override
                    public void pointHandler(float point) {
                        //TODO
                    }
                });
                getPointDialogBox.show();
            }
        });
    }

    private void setUpView(Context context) {
        pointRatingBar.setRating(item.getPoint());
        point.setText(String.valueOf(item.getPoint()));
        title.setText(item.getTitle());
        info.setText(item.getInfo());
        FullScreenGalleryCardViewHandler cardViewHandler = new FullScreenGalleryCardViewHandler(item.getImgUrls());
        gallery.setAdapter(cardViewHandler);
        gallery.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                false));
    }

    private void findView() {
        getPointBtn = findViewById(R.id.item_and_places_dialog_box_get_point);
        pointRatingBar  = findViewById(R.id.item_and_places_dialog_box_rating_bar);
        point = findViewById(R.id.item_and_places_dialog_box_place_point);
        title = findViewById(R.id.item_and_places_dialog_box_title);
        info = findViewById(R.id.item_and_places_dialog_box_info);
        gallery = findViewById(R.id.item_and_places_dialog_box_recycler_view);
    }
}
