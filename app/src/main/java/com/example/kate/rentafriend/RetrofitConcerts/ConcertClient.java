package com.example.kate.rentafriend.RetrofitConcerts;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConcertClient {

    private static Retrofit retrofit;

    private static final String BASE_URL = "https://api.songkick.com";

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
