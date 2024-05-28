package com.lavrent.weatherapp.ViewModel

import androidx.lifecycle.ViewModel
import com.lavrent.weatherapp.Repository.WeatherRepository
import com.lavrent.weatherapp.Server.ApiClient
import com.lavrent.weatherapp.Server.ApiServices

class WeatherViewModel(val repository: WeatherRepository):ViewModel() {

    constructor():this(WeatherRepository(ApiClient().getClient().create(ApiServices::class.java)))

    fun loadCurrentWeather(lat : Double, lng : Double, unit : String) =
        repository.getCurrentWeather(lat, lng, unit)
}