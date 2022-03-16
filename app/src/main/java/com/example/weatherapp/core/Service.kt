package com.example.weatherapp.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseUrl.Url_static)
            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }
}

object BaseUrl{
    const val Url_static = "https://api.openweathermap.org/data/2.5/"
    const val Api_key = "3b2bef720a2394e9ab69d4311ca8a1ee"
}