package com.example.busful;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Camera;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class Maps extends AppCompatActivity implements OnMapReadyCallback {

    private List<LatLng> latLngList;
    private String coords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        latLngList = new ArrayList<>();

        coords = getIntent().getExtras().getString("routes");
        parseString();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(latLngList);
        polylineOptions.width(5).color(Color.RED);
        googleMap.addPolyline(polylineOptions);
        googleMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(46.069063, 23.570662), 12));
    }

    private void parseString() {
        coords = coords.replace(" ", "");
        String[] p1 = this.coords.split("\\+");
        for (String s : p1) {
            String[] aux = s.split(",");
            latLngList.add(new LatLng(Double.parseDouble(aux[0]), Double.parseDouble(aux[1])));
        }

    }
}