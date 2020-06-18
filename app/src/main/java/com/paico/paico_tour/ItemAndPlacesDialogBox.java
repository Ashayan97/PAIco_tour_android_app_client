package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
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
        findView();
        setUpView(context);
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
