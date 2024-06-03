package com.lavrent.weatherapp.viewModel

import androidx.lifecycle.ViewModel
import com.lavrent.weatherapp.repository.CityRepository
import com.lavrent.weatherapp.server.ApiClient
import com.lavrent.weatherapp.server.ApiServices
import org.intellij.lang.annotations.Language

class CityViewModel(private val repository: CityRepository) : ViewModel() {
    constructor(): this(CityRepository(ApiClient().getClient().create(ApiServices::class.java)))

    fun loadCity(q:String, limit: Int) =
        repository.getCities(q, limit)

}