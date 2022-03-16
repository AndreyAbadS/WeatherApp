package com.example.weatherapp.ui.bodyApp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentDetailsWeatherBinding

class detailsWeatherFragment : Fragment(R.layout.fragment_details_weather) {
    private lateinit var binding: FragmentDetailsWeatherBinding
    private val args by navArgs<detailsWeatherFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsWeatherBinding.bind(view)
        var city = args.city
        var citycountry = args.citycountry
        var temp = args.temp
        var feelslike = args.feelsLike
        var date = args.date
        var description = args.description
        var status = args.main
        changeImage(status)
        binding.tvCity.text = args.city
        binding.tvCountry.text = args.citycountry
        binding.tvTempDetail.text = args.temp.toString()
        binding.tvDescription.text = args.description
        binding.tvTempFeelsLike.text = args.feelsLike.toString()
        binding.tvStatusDetails.text = args.main
        binding.tvDate.text = args.date

    }

    fun changeImage(type:String){
        if (type=="Clouds"){
            binding.imWeather.setImageResource(R.drawable.ic_baseline_cloud_24)
        }
        if (type=="Rain"){
            binding.imWeather.setImageResource(R.drawable.ic_baseline_beach_access_24)
        }
        if (type=="Clear"){
            binding.imWeather.setImageResource(R.drawable.ic_baseline_wb_sunny_24)
        }
    }
}