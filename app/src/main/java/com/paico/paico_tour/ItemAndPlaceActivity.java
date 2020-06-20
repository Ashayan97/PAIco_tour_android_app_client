package com.paico.paico_tour;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paico.paico_tour.object_classes.Item;

import java.util.ArrayList;

public class ItemAndPlaceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_and_places);
        findView();
    }

    private void findView() {
        recyclerView = findViewById(R.id.item_and_places_recycle_view);
        ItemAndPlacesCardViewHandler handler = new ItemAndPlacesCardViewHandler(new ArrayList<Item>(),this);
        recyclerView.setAdapter(handler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
