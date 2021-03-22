package com.example.kate.rentafriend.RetrofitConcerts.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {

    @SerializedName("event")
    List<Event> event;

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    public Results(List<Event> event) {
        this.event = event;
    }
}
