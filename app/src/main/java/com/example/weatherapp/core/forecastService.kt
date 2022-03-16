package com.example.weatherapp.core

import com.example.weatherapp.data.callService.weatherData
import com.example.weatherapp.data.model.ForecastApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class forecastService {
    private val retrofit = Service.getRetrofit()

    suspend fun getForecast(city:String,key:String):weatherData{
        lateinit var weatherData:weatherData
        withContext(Dispatchers.IO){
            weatherData =retrofit.create(ForecastApiClient::class.java).getCity(city,key)

        }
        return weatherData
    }
}