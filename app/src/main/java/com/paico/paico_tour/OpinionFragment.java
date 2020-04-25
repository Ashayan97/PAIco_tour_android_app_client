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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        ArrayList<Opinion> opinionList;
        //TODO opinionList = getOpinionList();
        //TODO remove below lines and add server API
        Places[] places=new Places[1];
        Places places1=new Places();
        String[] img=new String[2];
        img[0]="https://upload.wikimedia.org/wikipedia/commons/8/8d/Atashkadeh.jpg";
        img[1]="https://www.itto.org/iran/image-bin/182212022751qu46coa1vz.jpg?fillit=700x420";
        places1.setRate(3/2);
        places1.setImgUrls(img);
        places1.setName("AtashKadeh");
        places1.setDescription("The Fire Temple of Yazd (آتشکده یزد), also known as Yazd Atash Behram (Persian: یزد آتش بهرام), is a Zoroastrian fire temple in Yazd, Yazd province, Iran. It enshrines the Atash Bahram, meaning “Victorious Fire”, dated to 470 AD. It is one of the nine Atash Bahrams, the only one of the highest grade fire in ancient Iran where Zoroastrians have practiced their religion since 400 BC; the other eight Atash Bahrams are in India.[1][2] According to Aga Rustam Noshiravan Belivani, of Sharifabad, the Anjuman-i Nasiri (elected Zoroastrian officials) opened the Yazd Atash Behram in the 1960s to non-Zoroastrian visitors.\n" +
                "\n" +
                "Seeing a few children, firebox in their hand, and their occasional naughtiness but with caution not to fall into the ground was a common sight in ancient Iran. The children who like fire were the heat and kindness of every family. At that time, there was no match or other instrument to make fire. Thus, in one place, a fire was always on so that the people can take an amount of fire daily to turn on the firebox of their house. “Kadeh” in Dari Persian language means house, and “Atashkadeh” means the house of fire.");
        places1.setAddress("yazd some where you can not find it easily :)");
        places1.setVideoUrl("https://as4.cdn.asset.aparat.com/aparat-video/91966e6387c6859d06e57c2e4a2a858620887300-240p.mp4");
        places1.setProfilePicUrl("https://upload.wikimedia.org/wikipedia/commons/8/8d/Atashkadeh.jpg");
        places1.setHourTime("3pm_2am");
        places1.setAdministration("shayan");
        places1.setPhoneNumber("+98521864324");
        String[] imgUrl1=new String[1];
        imgUrl1[0]="https://upload.wikimedia.org/wikipedia/commons/8/8d/Atashkadeh.jpg";
        places[0]=places1;


        opinionList=new ArrayList<>();
        Opinion fake=new Opinion("shayan","shiraz was wonder full and great and blah blah blah blah blah blah blah blah blah blah",4,"fake Address");
        fake.setGalleryUrls(imgUrl1);
        fake.setPlacesGallery(places);
        fake.setPlaces(places);
        opinionList.add(fake);
        Opinion fake2=new Opinion("sshayan","shiraz was wonder full and great and blah blah blah blah blah blah blah blah blah blah",(float)4.5,"fake Address");
        fake2.setGalleryUrls(imgUrl1);
        fake2.setPlacesGallery(places);
        fake2.setPlaces(places);
        opinionList.add(fake2);
        Opinion fake3=new Opinion("sshayan1997","shiraz was wonder full and great and blah blah blah blah blah blah blah blah blah blah",(float)3.25,"fake Address");
        fake3.setGalleryUrls(imgUrl1);
        fake3.setPlacesGallery(places);
        fake3.setPlaces(places);
        opinionList.add(fake3);
        Opinion fake4=new Opinion("shayan97","shiraz was wonder full and great and blah blah blah blah blah blah blah blah blah blah",2,"fake Address");
        fake4.setGalleryUrls(imgUrl1);
        fake4.setPlacesGallery(places);
        fake4.setPlaces(places);
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
        OpinionViewCardViewHandler opinionAdapter = new OpinionViewCardViewHandler(opinionList,getContext());
        opinionListView.setAdapter(opinionAdapter);
        opinionListView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}


