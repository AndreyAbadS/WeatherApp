package com.example.weatherapp.domain

import com.example.weatherapp.data.callService.weatherData
import com.example.weatherapp.repository.forecastRepository

class getForecastCase() {
    private val repository= forecastRepository()
    suspend operator fun invoke(city:String,key:String): weatherData ?= repository.getForecast(city,key)
}