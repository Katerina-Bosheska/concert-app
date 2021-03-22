package com.example.kate.rentafriend.RetrofitMetroAreaID.Models;

import com.google.gson.annotations.SerializedName;

public class MetroArea {

    @SerializedName("id")
    int id;

    public MetroArea(int id) {
        this.id = id;
    }

    public String getId() {
        return id + "";
    }

    public void setId(int id) {
        this.id = id;
    }
}
