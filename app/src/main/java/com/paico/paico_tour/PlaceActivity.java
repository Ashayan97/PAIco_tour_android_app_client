package com.paico.paico_tour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.paico.paico_tour.object_classes.Item;
import com.paico.paico_tour.object_classes.Places;

public class PlaceActivity extends AppCompatActivity {

    private Places place;

    private RecyclerView gallery;
    private VideoView videoView;
    private TextView placeInfo;
    private TextView history;
    private TextView title;
    private Button ticket;
    private Button direction;
    private Button liveInfo;
    private Button placesAndItem;
    private Button givePoint;
    private ScrollView scrollView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        //TODO remove next lines
        Gson gson = new Gson();
        place = gson.fromJson(getIntent().getStringExtra("place"), Places.class);
        //////
        findView();
        setView();
        onClick();
    }

    private void setView() {
        scrollView.smoothScrollTo(0,0);
        videoView.setVideoPath(place.getVideoUrl());
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
        placeInfo.setText(getString(R.string.place_info_form, place.getName(), place.getHourTime(), place.getAdministration(), place.getPhoneNumber()));
        title.setText(place.getName());
        history.setText(place.getDescription());
        PlaceFragmentGalleryCardViewHandler cardViewHandler = new PlaceFragmentGalleryCardViewHandler(place.getImgUrls());
        gallery.setAdapter(cardViewHandler);
        gallery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
    }

    private void onClick() {
        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        liveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        placesAndItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PlaceActivity.this,ItemAndPlaceActivity.class);
                Item item = new Item();
                //TODO remove next lines
                item.setInfo("Apart from relatively minor fire temples, three were said to derive directly from Ahura Mazda, thus making them the most important in Zoroastrian tradition. These were the \"Great Fires\" or \"Royal Fires\" of Adur Burzen-Mihr, Adur Farnbag, and Adur Gushnasp. The legends of the Great Fires are probably of antiquity (see also Denkard citation, below), for by the 3rd century CE, miracles were said to happen at the sites, and the fires were popularly associated with other legends such as those of the folktale heroes Fereydun, Jamshid and Rustam.\n" +
                        "\n" +
                        "The Bundahishn, an encyclopaediaic collection of Zoroastrian cosmogony and cosmology written in Book Pahlavi,[19] which was finished in the 11th or 12th century CE, states that the Great Fires had existed since creation and had been brought forth on the back of the ox Srishok to propagate the faith, dispel doubt, and protect all humankind. Other texts observe that the Great Fires were also vehicles of propaganda and symbols of imperial sovereignty.\n" +
                        "\n" +
                        "The priests of these respective \"Royal Fires\" are said to have competed with each other to draw pilgrims by promoting the legends and miracles that were purported to have occurred at their respective sites. Each of the three is also said to have mirrored social and feudal divisions: \"The fire which is Farnbag has made its place among the priests; ... the fire which is Gūshnasp has made its place among the warriors; ... the fire which is Būrzīn-Mitrō has made its place among agriculturists\" (Denkard, 6.293). These divisions are archaeologically and sociologically revealing, because they make clear that, since from at least the 1st century BCE onwards, society was divided into four, not three, feudal estates.");
                item.setImgProfile("https://intoday.ir/wp-content/uploads/2018/05/Zoroastrian_Fire_Temple_Intoday_Ir-3-780x470.jpg");
                item.setPoint(3/2);
                item.setTitle("Yazd Eternal Fire");
                String urls[]=new String[1];
                urls[0]="https://intoday.ir/wp-content/uploads/2018/05/Zoroastrian_Fire_Temple_Intoday_Ir-3-780x470.jpg";
                item.setImgUrls(urls);
                Gson gson=new Gson();
                String newClass=gson.toJson(item);
                intent.putExtra("items",newClass);
                ////////////////////////////////////////////
                startActivity(intent);
            }
        });
        givePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPointDialogBox getPointDialogBox = new GetPointDialogBox(PlaceActivity.this, new PointHandler() {
                    @Override
                    public void pointHandler(float point) {
                        //TODO
                    }
                });
                getPointDialogBox.show();
            }
        });
    }

    private void findView() {
        gallery = findViewById(R.id.place_fragment_gallery);
        videoView = findViewById(R.id.place_fragment_video_view);
        placeInfo = findViewById(R.id.place_fragment_info_form);
        history = findViewById(R.id.place_fragment_description);
        title = findViewById(R.id.place_fragment_title);
        ticket = findViewById(R.id.place_fragment_ticket);
        direction = findViewById(R.id.place_fragment_direction);
        liveInfo = findViewById(R.id.place_fragment_live_info);
        givePoint = findViewById(R.id.place_fragment_give_point);
        placesAndItem = findViewById(R.id.place_fragment_item_and_places);
        scrollView = findViewById(R.id.place_fragment_scroll_view);

    }

}
