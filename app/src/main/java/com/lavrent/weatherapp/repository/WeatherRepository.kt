package com.lavrent.weatherapp.repository

import com.lavrent.weatherapp.server.ApiServices

class WeatherRepository(private val api:ApiServices) {

    fun getCurrentWeather(lat:Double, lng:Double, units:String) =
        api.getCurrentWeather(lat,lng,units,"a40d44656e0f68abe75c93009329b928")

    fun getForecastWeather(lat:Double, lng:Double, units:String) =
        api.getForecastWeather(lat,lng,units,"a40d44656e0f68abe75c93009329b928")
}