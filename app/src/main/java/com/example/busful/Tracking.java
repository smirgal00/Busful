package com.example.busful;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Camera;
import android.os.Bundle;
import android.os.CpuUsageInfo;

import com.example.busful.Database.CoordHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tracking extends AppCompatActivity implements OnMapReadyCallback {

    private DatabaseReference databaseReference;
    private CoordHelper coordHelper;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        coordHelper = new CoordHelper(1.0, 2.0);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        databaseReference = FirebaseDatabase.getInstance()
                .getReference()
                .child("Tracking");

        //coordHelper = new CoordHelper(1.0, 2.0);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                coordHelper = snapshot.getValue(CoordHelper.class);

                mapFragment.getMapAsync(Tracking.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (!coordHelper.getLat().equals(null)) {
            LatLng latLng = new LatLng(coordHelper.getLat(), coordHelper.getLng());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Locatie curenta:" + latLng.latitude + " " + latLng.longitude);
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.clear();
            googleMap.addMarker(markerOptions);
        }
    }


}