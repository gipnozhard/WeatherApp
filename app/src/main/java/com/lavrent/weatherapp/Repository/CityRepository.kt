package com.lavrent.weatherapp.Repository

import androidx.viewpager2.widget.ViewPager2.OffscreenPageLimit
import com.lavrent.weatherapp.Server.ApiServices

class CityRepository(val api: ApiServices) {
    fun getCities(q: String, limit: Int) =
        api.getCitiesList(q, limit, "a40d44656e0f68abe75c93009329b928")
}