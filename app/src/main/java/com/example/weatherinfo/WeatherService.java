package com.example.weatherinfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService{

    @GET("data/2.5/weather/")
    Call<WeatherResponse> getWeatherData(@Query("q") String q, @Query("appid") String appid);
}
