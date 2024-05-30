package com.lavrent.weatherapp.repository

import com.lavrent.weatherapp.server.ApiServices

class CityRepository(private val api: ApiServices) {
    fun getCities(q: String, limit: Int) =
        api.getCitiesList(q, limit, "a40d44656e0f68abe75c93009329b928")
}