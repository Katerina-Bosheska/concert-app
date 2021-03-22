package com.example.kate.rentafriend.RetrofitMetroAreaID.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {

    @SerializedName("metroArea")
    MetroArea metroArea;

    public MetroArea getMetroArea() {
        return metroArea;
    }

    public Location(MetroArea metroArea) {
        this.metroArea = metroArea;
    }
}
