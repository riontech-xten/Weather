package com.xtensolutions.weatherapp.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.xtensolutions.weatherapp.core.DataResult
import com.xtensolutions.weatherapp.data.model.ForecastData
import com.xtensolutions.weatherapp.databinding.FragmentCityWeatherBinding
import com.xtensolutions.weatherapp.extension.isTablet
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import com.xtensolutions.weatherapp.ui.adapter.ForecastAdapter
import com.xtensolutions.weatherapp.ui.viewmodel.WeatherViewModel
import com.xtensolutions.weatherapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
@AndroidEntryPoint
class CityWeatherFragment : Fragment() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: FragmentCityWeatherBinding
    private var city: BookmarkCity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.unitCode = Constants.SYS_CELSIUS
        if (requireArguments().containsKey("city")) {
            city = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requireArguments().getSerializable("city", BookmarkCity::class.java)
            } else {
                requireArguments().getSerializable("city") as BookmarkCity
            }
            binding.txtCityName.text = city?.name
            binding.unitCode = viewModel.getUnitsCode()
            getTodayWeather()
            get5DayForecast()
        }
    }

    private fun getTodayWeather() {
        city?.let {
            viewModel.getTodayWeather(it.latitude, it.longitude).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is DataResult.Success -> {
                        binding.weather = result.data
                        binding.unitCode = viewModel.getUnitsCode()
                        binding.executePendingBindings()
                    }

                    is DataResult.Fail -> showFailMsg(result.message)
                    else -> binding.txt5dayDataLoading.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun get5DayForecast() {
        city?.let {
            viewModel.get5DaysForecast(it.latitude, it.longitude).observe(viewLifecycleOwner) { result ->
                binding.txt5dayDataLoading.visibility = View.GONE
                when (result) {
                    is DataResult.Success -> setupForecastData(result.data)
                    is DataResult.Fail -> showFailMsg(result.message)
                    else -> binding.txt5dayDataLoading.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun showFailMsg(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun setupForecastData(data: ForecastData?) {
        var layoutManager = LinearLayoutManager(requireContext())
        if (requireContext().isTablet())
            layoutManager = GridLayoutManager(requireContext(), 3)

        binding.forecastList.layoutManager = layoutManager
        val adapter = ForecastAdapter(requireContext(), data!!.list.toMutableList())
        adapter.unit = viewModel.getUnitsCode()
        binding.forecastList.adapter = adapter
    }
}
