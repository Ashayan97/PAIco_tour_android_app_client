package com.paico.paico_tour;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paico.paico_tour.object_classes.Places;
import com.paico.paico_tour.object_classes.PlacesLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OpinionFragment extends Fragment {
    private RecyclerView opinionListView;
    private FloatingActionButton addOpinionFloatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.opinion_about_city, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        onClick();
        configureRecyclerView(view);
    }

    private void onClick() {
        addOpinionFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpinionDialogGetOpinionBox opinionDialogGetOpinionBox=new OpinionDialogGetOpinionBox(getContext());
                opinionDialogGetOpinionBox.show();
            }
        });
    }

    private void findView(View view) {
        addOpinionFloatingActionButton =view.findViewById(R.id.opinion_add_new_opinion);
    }


    private void configureRecyclerView(View view) {
        opinionListView = (RecyclerView) view.findViewById(R.id.opinion_recycler_view);
        ArrayList<Opinion> opinionList = new ArrayList<>();
        //TODO opinionList = getOpinionList();
        //TODO remove below lines and add server API
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Suggests");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int size = dataSnapshot.child("size").getValue(Integer.class);
                for (int i = 0; i < size; i++) {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                Log.d("user_info", databaseError.getMessage());
            }
        });


        Comparator<Opinion> comparator = new Comparator<Opinion>() {
            @Override
            public int compare(Opinion o1, Opinion o2) {
                if (o1.getRate() >= o2.getRate())
                    return -1;
                if (o1.getRate() < o2.getRate())
                    return 1;
                return 0;
            }
        };
//        Collections.sort(opinionList, comparator);
//        OpinionViewCardViewHandler opinionAdapter = new OpinionViewCardViewHandler(opinionList,getContext());
//        opinionListView.setAdapter(opinionAdapter);
//        opinionListView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}


