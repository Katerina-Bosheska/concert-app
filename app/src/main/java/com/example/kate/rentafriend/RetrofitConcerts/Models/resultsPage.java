package com.example.kate.rentafriend.RetrofitConcerts.Models;

import com.google.gson.annotations.SerializedName;

public class resultsPage {

    @SerializedName("results")
    Results results;

    public resultsPage(Results results) {
        this.results = results;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}