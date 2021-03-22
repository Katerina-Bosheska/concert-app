package com.example.kate.rentafriend.RetrofitMetroAreaID;

import com.example.kate.rentafriend.RetrofitMetroAreaID.Models.JsonResponse;
import com.example.kate.rentafriend.RetrofitMetroAreaID.Models.resultsPage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MetroAreaIdInterface {

    @GET("/api/3.0/search/locations.json?apikey=CIvE4b3Uh6JkG3AU")
    Call<JsonResponse> getMetroAreaID(@Query("query") String cityName);
}
