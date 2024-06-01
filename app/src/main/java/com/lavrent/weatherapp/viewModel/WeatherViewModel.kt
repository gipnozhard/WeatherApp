package com.lavrent.weatherapp.viewModel

import androidx.lifecycle.ViewModel
import com.lavrent.weatherapp.repository.WeatherRepository
import com.lavrent.weatherapp.server.ApiClient
import com.lavrent.weatherapp.server.ApiServices

class WeatherViewModel(private val repository: WeatherRepository):ViewModel() {

    constructor():this(WeatherRepository(ApiClient().getClient().create(ApiServices::class.java)))

    fun loadCurrentWeather(lat : Double, lng : Double, unit : String) =
        repository.getCurrentWeather(lat, lng, unit)

    fun loadForecastWeather(lat : Double, lng : Double, unit : String) =
        repository.getForecastWeather(lat, lng, unit)

    fun loadCurrentTimeDate(lat : Double, lng : Double, unit : String) =
        repository.getCurrentWeather(lat, lng, unit)
}