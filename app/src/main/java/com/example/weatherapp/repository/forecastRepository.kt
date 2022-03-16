package com.example.weatherapp.repository

import com.example.weatherapp.core.forecastService
import com.example.weatherapp.data.callService.weatherData

class forecastRepository {
    private val api= forecastService()
    suspend fun getForecast(city:String, key:String): weatherData{
        return api.getForecast(city,key)
    }
}