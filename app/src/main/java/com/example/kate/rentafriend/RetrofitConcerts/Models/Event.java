package com.example.kate.rentafriend.RetrofitConcerts.Models;

import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("id")
    int id;

    @SerializedName("displayName")
    String displayName;

    @SerializedName("start")
    Start start;

    @SerializedName("venue")
    Venue venue;

    @SerializedName("location")
    Location location;

    public Event(int id, String displayName, Start start, Venue venue) {
        this.id = id;
        this.displayName = displayName;
        this.start = start;
        this.venue = venue;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getId() {
        return id+"";
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

}
