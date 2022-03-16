package com.example.weatherapp.ui.bodyApp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSarchCityBinding
import com.example.weatherapp.repository.forecastViewModel
import kotlin.math.log

class sarchCityFragment : Fragment(R.layout.fragment_sarch_city) {
    private lateinit var binding: FragmentSarchCityBinding
    lateinit var date: String
    lateinit var description: String
    lateinit var main: String
    lateinit var city: String
    var feels_like: Int = 0
    var temp: Int = 0
    lateinit var cityCountry: String
    private val forecastViewModel: forecastViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSarchCityBinding.bind(view)

        binding.btnSearchCity.setOnClickListener {
            forecastViewModel.GetData(
                binding.edSearchCity.text.toString(),
                "3b2bef720a2394e9ab69d4311ca8a1ee"
            )
        }
        forecastViewModel.weatherData.observe(viewLifecycleOwner) { current ->

            city = current.city.name
            cityCountry = current.city.country
            for ((i, List) in current.list.withIndex()) {
                date = List.dt_txt
                feels_like = List.main.feels_like.toInt()
                temp = List.main.temp.toInt()

                Log.d("for", List.dt_txt)
                for ((e, actual2) in List.weather.withIndex()) {
                    main = actual2.main
                    description = actual2.description
                }


            }
            Log.d("hola", description)
            Log.d("hola", main)
            Log.d("hola", temp.toString())
            Log.d("hola", feels_like.toString())
            Log.d("hola", date)
            Log.d("hola", cityCountry)
            Log.d("hola", city)
            val action =
                sarchCityFragmentDirections.actionSarchCityFragmentToStatusWeatherFragment(
                    feels_like,
                    temp,
                    main,
                    city,
                    date,
                    cityCountry,
                    description
                )
            findNavController().navigate(action)

        }
    }

}