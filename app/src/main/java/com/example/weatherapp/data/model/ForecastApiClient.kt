package com.example.weatherapp.data.model

import com.example.weatherapp.core.BaseUrl
import com.example.weatherapp.core.Service
import com.example.weatherapp.data.callService.weatherData
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForecastApiClient {
    @GET("forecast?")
    suspend fun getCity(@Query("q")id: String,@Query("appid")key:String): weatherData

    object retrofitWeb{
        val website by lazy {
            Retrofit.Builder()
                .baseUrl(BaseUrl.Url_static)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(ForecastApiClient::class.java)
        }
    }
}