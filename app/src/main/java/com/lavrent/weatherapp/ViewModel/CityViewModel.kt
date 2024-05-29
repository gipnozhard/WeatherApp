package com.lavrent.weatherapp.ViewModel

import androidx.lifecycle.ViewModel
import com.lavrent.weatherapp.Repository.CityRepository
import com.lavrent.weatherapp.Server.ApiClient
import com.lavrent.weatherapp.Server.ApiServices

class CityViewModel(val repository: CityRepository) : ViewModel() {
    constructor(): this(CityRepository(ApiClient().getClient().create(ApiServices::class.java)))

    fun loadCity(q:String, limit: Int) =
        repository.getCities(q, limit)
}