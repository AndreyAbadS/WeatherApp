package com.example.weatherapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.callService.weatherData
import com.example.weatherapp.domain.getForecastCase
import kotlinx.coroutines.launch

class forecastViewModel:ViewModel() {
    var weatherData = MutableLiveData<weatherData>()
    var getWeatherUseCase = getForecastCase()
    val isLoading = MutableLiveData<Boolean>()

    fun GetData(city:String, key:String){
        viewModelScope.launch {
            val result = getWeatherUseCase(city,key)
            weatherData.postValue(result!!)
            Log.d("weather",result.toString())
        }
    }
}