package com.paico.paico_tour;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OpinionFragment extends Fragment {
    private RecyclerView opinionListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.opinion_about_city, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureRecyclerView(view);
    }


    private void configureRecyclerView(View view) {
        opinionListView = (RecyclerView) view.findViewById(R.id.opinion_recycler_view);
        ArrayList<Opinion> opinionList;
        //TODO opinionList = getOpinionList();
        //TODO remove below lines and add server API
        opinionList=new ArrayList<>();
        Opinion fake=new Opinion("shayan","shiraz was wonder full and great and blah blah blah blah blah blah blah blah blah blah",4,"fake Address");
        opinionList.add(fake);
        Opinion fake2=new Opinion("sshayan","shiraz was wonder full and great and blah blah blah blah blah blah blah blah blah blah",(float)4.5,"fake Address");
        opinionList.add(fake2);
        Opinion fake3=new Opinion("sshayan1997","shiraz was wonder full and great and blah blah blah blah blah blah blah blah blah blah",(float)3.25,"fake Address");
        opinionList.add(fake3);
        Opinion fake4=new Opinion("shayan97","shiraz was wonder full and great and blah blah blah blah blah blah blah blah blah blah",2,"fake Address");
        opinionList.add(fake4);


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
        Collections.sort(opinionList, comparator);
        OpinionViewCardViewHandler opinionAdapter = new OpinionViewCardViewHandler(opinionList);
        opinionListView.setAdapter(opinionAdapter);
        opinionListView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}


