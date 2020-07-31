package com.paico.paico_tour.object_classes;


import java.util.ArrayList;

public class PlacesLoader {

    private static PlacesLoader instance;

    private static ArrayList<Places> placesArrayList;

    public static PlacesLoader getInstance() {
        if (instance == null)
            instance = new PlacesLoader();
        return instance;
    }

    private PlacesLoader(){
        placesArrayList = new ArrayList<>();
    }


    public static ArrayList<Places> getPlacesArrayList() {
        return placesArrayList;
    }

    public static void setPlacesArrayList(ArrayList<Places> placesArrayList) {
        PlacesLoader.placesArrayList = placesArrayList;
    }
    public static void setPlacesArrayListItem(ArrayList<Places> placesArrayList) {
        PlacesLoader.placesArrayList = placesArrayList;
    }
}
