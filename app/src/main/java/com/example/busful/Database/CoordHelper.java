package com.example.busful.Database;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class CoordHelper {
    private Double lat;
    private Double lng;


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public CoordHelper(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public CoordHelper() {

    }


}
