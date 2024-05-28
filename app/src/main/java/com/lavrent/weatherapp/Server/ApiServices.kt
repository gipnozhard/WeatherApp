package com.lavrent.weatherapp.Server

import retrofit2.Call
import com.lavrent.weatherapp.model.CurrentReponseApi
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiServices {

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("units") unit:String,
        @Query("appid") ApiKey:String,
        ):Call<CurrentReponseApi>
}