package com.example.busful.Database;

import com.example.busful.History;

public class HistoryInfo {
    private String email;
    private String routeID;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public HistoryInfo () {

    }

    public HistoryInfo(String email, String routeID) {
        this.email = email;
        this.routeID = routeID;
    }
}
