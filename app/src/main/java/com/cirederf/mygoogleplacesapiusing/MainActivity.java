package com.cirederf.mygoogleplacesapiusing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    PlacesClient placesClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey="AIzaSyAQIMmBdFBM6kVUJ5HyC7tpUXKbkIow_lI";
        if(!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }
        placesClient = Places.createClient(this);
        final AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteSupportFragment.setPlaceFields (Arrays.asList (Place.Field.ID, Place.Field.LAT_LNG, Place.Field.NAME));
        //setPlaceFiels: use for the purpose when they autocompleteplaces: returns thze places and if click on place than what field you gonna get after clik on that specifiq place

        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                final LatLng latLng = place.getLatLng();
                Toast.makeText(MainActivity.this, ""+latLng.latitude, Toast.LENGTH_SHORT).show();
                Log.i("PlacesApi", "on place selected: " +latLng.latitude+"\n" +latLng.longitude);
                //don,t forget the internet permission :p in manifest
            }
            @Override
            public void onError(@NonNull Status status) {

            }
        });
    }
    //here we search something and disply the results in a window clikable. it's not what i want...
}