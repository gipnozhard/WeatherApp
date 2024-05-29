package com.lavrent.weatherapp.Server

import retrofit2.Call
import com.lavrent.weatherapp.model.CurrentResponseApi
import com.lavrent.weatherapp.model.ForecastResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("units") unit:String,
        @Query("appid") ApiKey:String,
        ):Call<CurrentResponseApi>

    @GET("data/2.5/forecast")
    fun getForecastWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("units") unit:String,
        @Query("appid") ApiKey:String,
    ):Call<ForecastResponseApi>
}