package com.lavrent.weatherapp.Activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.matteobattilana.weather.PrecipType
import com.lavrent.weatherapp.R
import com.lavrent.weatherapp.ViewModel.WeatherViewModel
import com.lavrent.weatherapp.databinding.ActivityMainBinding
import com.lavrent.weatherapp.model.CurrentReponseApi
import retrofit2.Call
import retrofit2.Response
import java.util.Calendar
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val weatherViewModel:WeatherViewModel by viewModels()
    private val calendar by lazy { Calendar.getInstance() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }

        binding.apply {
            var lat = 51.50
            var lon = -0.12
            var name = "London"

            cityTxt.text = name
            progressBar.visibility = View.VISIBLE
            weatherViewModel.loadCurrentWeather(lat,lon,"metric").enqueue(object :
                retrofit2.Callback<CurrentReponseApi> {
                override fun onResponse(
                    call: Call<CurrentReponseApi>,
                    response: Response<CurrentReponseApi>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()
                        progressBar.visibility = View.GONE
                        detailLayout.visibility = View.VISIBLE
                        data?.let {
                            statusTxt.text = it.weather?.get(0)?.main ?: "-"
                            windTxt.text = it.wind?.speed?.let { Math.round(it).toString() } + "Km"
                            currentTempTxt.text =
                                it.main?.temp?.let { Math.round(it).toString() } + "॰"
                            maxTempTxt.text =
                                it.main?.tempMax?.let { Math.round(it).toString() } + "॰"
                            minTextTxt.text =
                                it.main?.tempMin?.let { Math.round(it).toString() } + "॰"

                            val drawable = if (isNightNow()) R.drawable.night_bg
                            else {
                                setDynamicallyWallpaper(it.weather?.get(0)?.icon?: "-")
                            }
                            bgImage.setImageResource(drawable)
                            setEffectRainSnow(it.weather?.get(0)?.icon?: "-")
                        }
                    }
                }

                override fun onFailure(call: Call<CurrentReponseApi>, t: Throwable) {
                    Toast.makeText(this@MainActivity,t.toString(), Toast.LENGTH_SHORT).show()
                }

            })
        }

    }

    private fun isNightNow():Boolean {
      return calendar.get(Calendar.HOUR_OF_DAY) >= 18
    }

    private fun setDynamicallyWallpaper(icon: String): Int {
        return  when(icon.dropLast(1)) {
            "01" -> {
                initWeatherView(PrecipType.CLEAR)
                R.drawable.snow_bg
            }
            "02", "03", "04" -> {
                initWeatherView(PrecipType.CLEAR)
                R.drawable.cloudy_bg
            }
            "09", "10", "11" -> {
                initWeatherView(PrecipType.RAIN)
                R.drawable.rainy_bg
            }
            "13" -> {
                initWeatherView(PrecipType.SNOW)
                R.drawable.snow_bg
            }
            "50" -> {
                initWeatherView(PrecipType.CLEAR)
                R.drawable.haze_bg
            }
            else -> 0
        }
    }
    private fun setEffectRainSnow(icon: String) {
        when(icon.dropLast(1)) {
            "01" -> {
                initWeatherView(PrecipType.CLEAR)

            }
            "02", "03", "04" -> {
                initWeatherView(PrecipType.CLEAR)

            }
            "09", "10", "11" -> {
                initWeatherView(PrecipType.RAIN)

            }
            "13" -> {
                initWeatherView(PrecipType.SNOW)

            }
            "50" -> {
                initWeatherView(PrecipType.CLEAR)

            }

        }
    }
    private fun initWeatherView(type: PrecipType) {
        binding.weatherView.apply {
            setWeatherData(type)
            angle = -20
            emissionRate = 100.0f
        }
    }
}