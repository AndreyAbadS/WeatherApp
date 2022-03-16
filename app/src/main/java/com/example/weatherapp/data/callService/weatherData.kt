package com.example.weatherapp.data.callService

data class weatherData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ListWeather>,
    val message: Int
)