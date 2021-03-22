package com.example.kate.rentafriend.RetrofitConcerts.Models;

import com.google.gson.annotations.SerializedName;

public class metroArea {

    @SerializedName("displayName")
    String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public metroArea(String city) {
        this.city = city;
    }
}
