package com.example.weatherapp.ui.bodyApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.core.Resource
import com.example.weatherapp.databinding.FragmentStatusWeatherBinding
import com.example.weatherapp.repository.forecastViewModel

class statusWeatherFragment : Fragment(R.layout.fragment_status_weather) {
    private lateinit var binding: FragmentStatusWeatherBinding
    private val args by navArgs<statusWeatherFragmentArgs>()
    private val forecastViewModel: forecastViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStatusWeatherBinding.bind(view)
        binding.tvStatus.text = args.status
        binding.tvTemp.text = args.temp.toString()

        var status = args.status
        var city = args.city
        var citycountry = args.citycountry
        var temp = args.temp
        var feelslike = args.feelsLike
        var date = args.date
        var description = args.description
        forecastViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = it

        })
        binding.llContent.setOnClickListener {
            val action = statusWeatherFragmentDirections.actionStatusWeatherFragmentToDetailsWeatherFragment(feelslike,temp,status,description,city, date, citycountry)
            findNavController().navigate(action)
        }

    }
}