package com.example.busful.Database;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;

public class BusInfo {

    private String nr;
    private String route;

    public BusInfo(String nr, String route) {
        this.nr = nr;
        this.route = route;
    }

    @NonNull
    @Override
    public String toString() {
        return nr + "\n" + route;
    }

    public String getNr() {
        return this.nr;
    }

    public String route() {
        return this.route;
    }
}
