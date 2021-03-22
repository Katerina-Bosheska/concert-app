package com.example.kate.rentafriend.RetrofitConcerts;

import com.example.kate.rentafriend.RetrofitConcerts.Models.JsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConcertInterface {

    @GET("/api/3.0/metro_areas/{metro_area_id}/calendar.json?apikey=CIvE4b3Uh6JkG3AU")
    Call<JsonResponse> getConcerts(@Path("metro_area_id") String cityName, @Query("min_date") String min_date, @Query("max_date") String max_date);
}
