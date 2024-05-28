package com.lavrent.weatherapp.Repository

import com.lavrent.weatherapp.Server.ApiServices

class WeatherRepository(val api:ApiServices) {

    fun getCurrentWeather(lat:Double, lng:Double, units:String) =
        api.getCurrentWeather(lat,lng,units,"a40d44656e0f68abe75c93009329b928")
}