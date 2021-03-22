package com.example.kate.rentafriend.RetrofitConcerts.Models;

import com.google.gson.annotations.SerializedName;

public class Start {

    @SerializedName("date")
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Start(String date) {
        this.date = date;
    }
}
