package com.paico.paico_tour;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.paico.paico_tour.object_classes.Item;

import java.util.ArrayList;

public class ItemAndPlaceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Item testItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO remove next line
        test();
        /////////////
        setContentView(R.layout.item_and_places);
        findView();
    }

    private void test() {
        Gson gson = new Gson();
        testItems= gson.fromJson(getIntent().getStringExtra("items"), Item.class);
    }

    private void findView() {
        recyclerView = findViewById(R.id.item_and_places_recycle_view);
        ArrayList<Item> items=new ArrayList<Item>();
        items.add(testItems);
        ItemAndPlacesCardViewHandler handler = new ItemAndPlacesCardViewHandler(items,this);
        recyclerView.setAdapter(handler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
