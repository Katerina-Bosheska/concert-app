package com.example.kate.rentafriend.RetrofitConcerts.Models;

public class Location {

    float lat, lng;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public Location(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Location(){

    }
}