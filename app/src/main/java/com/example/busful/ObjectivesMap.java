package com.example.busful;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ObjectivesMap extends AppCompatActivity implements OnMapReadyCallback {

    private String loc;
    private String dest;
    private SupportMapFragment mapFragment;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectives_map);

        loc = getIntent().getExtras().getString("location");

        mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                .findFragmentById(R.id.map);

        databaseReference = FirebaseDatabase.getInstance()
                .getReference("objectives")
                .child(loc);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    dest = s.getValue().toString();
                }

                mapFragment.getMapAsync(ObjectivesMap.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions markerOptions = new MarkerOptions();

        String[] poz = dest.replace(" ", "").split(",");
        markerOptions.position(new LatLng(Double.parseDouble(poz[0]), Double.parseDouble(poz[1])));

        googleMap.clear();

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(Double.parseDouble(poz[0]), Double.parseDouble(poz[1])), 12));

        googleMap.addMarker(markerOptions);
    }
}