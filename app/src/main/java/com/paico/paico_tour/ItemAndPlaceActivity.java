package com.paico.paico_tour;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

    }
}
