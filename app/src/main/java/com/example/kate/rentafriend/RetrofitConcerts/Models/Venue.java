package com.example.kate.rentafriend.RetrofitConcerts.Models;


import com.google.gson.annotations.SerializedName;

public class Venue {

    @SerializedName("metroArea")
    metroArea metroArea;

    public com.example.kate.rentafriend.RetrofitConcerts.Models.metroArea getMetroArea() {
        return metroArea;
    }

    public void setMetroArea(com.example.kate.rentafriend.RetrofitConcerts.Models.metroArea metroArea) {
        this.metroArea = metroArea;
    }

    public Venue(com.example.kate.rentafriend.RetrofitConcerts.Models.metroArea metroArea) {
        this.metroArea = metroArea;
    }
}
