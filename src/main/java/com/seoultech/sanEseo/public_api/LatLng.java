package com.seoultech.sanEseo.public_api;

import jakarta.persistence.Embeddable;

@Embeddable
public class LatLng {
    private double lat;
    private double lng;

    public LatLng() {}

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    // Getter and Setter
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}