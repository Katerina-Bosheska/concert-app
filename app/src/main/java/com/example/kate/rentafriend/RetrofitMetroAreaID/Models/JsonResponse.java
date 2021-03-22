package com.example.kate.rentafriend.RetrofitMetroAreaID.Models;

import com.google.gson.annotations.SerializedName;

public class JsonResponse {

    @SerializedName("resultsPage")
    resultsPage resultsPage;

    public com.example.kate.rentafriend.RetrofitMetroAreaID.Models.resultsPage getResultsPage() {
        return resultsPage;
    }

    public void setResultsPage(com.example.kate.rentafriend.RetrofitMetroAreaID.Models.resultsPage resultsPage) {
        this.resultsPage = resultsPage;
    }

    public JsonResponse(com.example.kate.rentafriend.RetrofitMetroAreaID.Models.resultsPage resultsPage) {
        this.resultsPage = resultsPage;
    }
}
