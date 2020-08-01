package com.paico.paico_tour;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paico.paico_tour.object_classes.PlacesLoader;

import java.util.ArrayList;
import java.util.Arrays;


public class MapsActivity extends Fragment implements OnMapReadyCallback {


    private static final int MY_PERMISSIONS_REQUEST_FIND_LOCATION = 110;
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private FloatingActionButton menu;
    private FloatingActionButton pay;
    private FloatingActionButton charge;
    private FloatingActionButton currentLocation;
    private View view;
    private boolean butShow = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_maps, container, false);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        this.view = view;
        onClick();
        locationPerm();
        SupportMapFragment mapFragment = (SupportMapFragment) FragmentManager.findFragment(view.findViewById(R.id.map));
        mapFragment.getMapAsync(this);

    }

    private void onClick() {
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!butShow) {
                    ObjectAnimator transChanger = ObjectAnimator.ofFloat(
                            pay,
                            "Alpha",
                            0, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1f
                    );
                    transChanger.setDuration(500);
                    transChanger.start();
                    transChanger = ObjectAnimator.ofFloat(
                            charge,
                            "Alpha",
                            0, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1f
                    );
                    transChanger.setDuration(500);
                    transChanger.start();
                    transChanger = ObjectAnimator.ofFloat(
                            currentLocation,
                            "Alpha",
                            0, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1f
                    );
                    transChanger.setDuration(500);
                    transChanger.start();
                    pay.setVisibility(View.VISIBLE);
                    charge.setVisibility(View.VISIBLE);
                    currentLocation.setVisibility(View.VISIBLE);
                    pay.setClickable(true);
                    charge.setClickable(true);
                    currentLocation.setClickable(true);
                    butShow = true;

                } else {
                    ObjectAnimator transChanger = ObjectAnimator.ofFloat(
                            pay,
                            "Alpha",
                            1f, 0.9f, 0.8f, 0.7f, 0.6f, 0.5f, 0.4f, 0.3f, 0.2f, 0.1f, 0
                    );
                    transChanger.setDuration(500);
                    transChanger.start();
                    transChanger = ObjectAnimator.ofFloat(
                            charge,
                            "Alpha",
                            1f, 0.9f, 0.8f, 0.7f, 0.6f, 0.5f, 0.4f, 0.3f, 0.2f, 0.1f, 0
                    );
                    transChanger.setDuration(500);
                    transChanger.start();
                    transChanger = ObjectAnimator.ofFloat(
                            currentLocation,
                            "Alpha",
                            1f, 0.9f, 0.8f, 0.7f, 0.6f, 0.5f, 0.4f, 0.3f, 0.2f, 0.1f, 0
                    );
                    transChanger.setDuration(500);
                    transChanger.start();
                    pay.setVisibility(View.INVISIBLE);
                    charge.setVisibility(View.INVISIBLE);
                    currentLocation.setVisibility(View.INVISIBLE);
                    pay.setClickable(false);
                    charge.setClickable(false);
                    currentLocation.setClickable(false);
                    butShow = false;

                }
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BarcodeReaderActivity.class);
                getContext().startActivity(intent);
            }
        });
        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChargeDialogBox chargeDialogBox = new ChargeDialogBox(getContext());
                chargeDialogBox.show();
            }
        });
        currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findCurrentLocation();
            }
        });
    }

    private void findView(View view) {
        menu = view.findViewById(R.id.activity_maps_menu_floating_button);
        pay = view.findViewById(R.id.activity_maps_pay_floating_button);
        charge = view.findViewById(R.id.activity_maps_charge_floating_button);
        currentLocation = view.findViewById(R.id.activity_maps_location_floating_button);
    }

    private void markPlaces() {
        final GoogleMap googleMap = this.mMap;

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Places");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int size = dataSnapshot.child("size").getValue(Integer.class);
                for (int i = 0; i < size; i++) {
                    com.paico.paico_tour.object_classes.Places places = dataSnapshot.child(String.valueOf(i)).getValue(com.paico.paico_tour.object_classes.Places.class);
                    places.getImgUrls().remove(0);
                    PlacesLoader.getInstance().setPlacesArrayListItem(places);
                    LatLng atashkade = new LatLng(places.getLat(), places.getLongitude());
                    Marker marker = googleMap.addMarker(new MarkerOptions().position(atashkade).title(places.getName()));
                    marker.setTag(places);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                Log.d("user_info", databaseError.getMessage());
            }
        });


    }

    private void initializeMapAutocomplete() {
        Places.initialize(view.getContext(), "AIzaSyClf_TDlpG3cE3lxi8CEmOHxFAVHxVI0go");
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                FragmentManager.findFragment(view.findViewById(R.id.autocomplete_fragment));


// Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

// Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                if (place != null) {
                    // TODO: Get info about the selected place.
                    Log.i("TAG", "Place: " + place.getName() + ", " + place.getId());
//                LatLng currentLocation = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 18));
                }

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });
    }

    private void findCurrentLocation() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {

                            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            //TODO remove next line
                            mMap.addMarker(new MarkerOptions().position(currentLocation).title("my location"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 18));
                        } else {

                            LatLng dolatAbadGarden = new LatLng(31.8812, 54.3733);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dolatAbadGarden, 18));
                        }
                    }
                });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getTag() != null) {
                    com.paico.paico_tour.object_classes.Places places = (com.paico.paico_tour.object_classes.Places) marker.getTag();
                    MarkerDialogBox markerDialogBox = new MarkerDialogBox(getContext(), places);
                    markerDialogBox.show();
                }

                return false;
            }
        });
        markPlaces();
        initializeMapAutocomplete();
        // Add a marker in Sydney and move the camera
    }


    private void locationPerm() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Rationale response
                new AlertDialog.Builder(getContext()).setMessage("We need this" +
                        " permission to help you find nearest historical locations \nand help you to speed up in use of app. ").setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_FIND_LOCATION);
                    }
                }).show();

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_FIND_LOCATION);
            }
        } else {
            findCurrentLocation();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FIND_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    findCurrentLocation();
                } else {
                    findCurrentLocation();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


}
