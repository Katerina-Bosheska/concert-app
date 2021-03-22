package com.example.kate.rentafriend.RetrofitConcerts.Models;

import com.google.gson.annotations.SerializedName;

public class JsonResponse {

    @SerializedName("resultsPage")
    resultsPage resultsPage;

    public resultsPage getResultsPage() {
        return resultsPage;
    }

    public void setResultsPage(resultsPage resultsPage) {
        this.resultsPage = resultsPage;
    }

    public JsonResponse(resultsPage resultsPage) {
        this.resultsPage = resultsPage;
    }
}

