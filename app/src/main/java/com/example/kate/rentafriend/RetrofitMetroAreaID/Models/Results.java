package com.example.kate.rentafriend.RetrofitMetroAreaID.Models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {

    @SerializedName("location")
    List<Location> location;

    public Results(List<Location> location) {
        this.location = location;
    }

    public Location getLocation() {
        return location.get(0);
    }

}
