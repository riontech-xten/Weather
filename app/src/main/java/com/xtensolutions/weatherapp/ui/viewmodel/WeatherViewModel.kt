package com.xtensolutions.weatherapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.xtensolutions.weatherapp.data.preference.PreferenceHelper
import com.xtensolutions.weatherapp.data.weather.WeatherRepository
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import com.xtensolutions.weatherapp.utils.Constants
import kotlinx.coroutines.launch


/**
 * Created by Vaghela Mithun R. on 17/02/21.
 * vaghela.mithun@gmail.com
 */

class WeatherViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    private val preferenceHelper: PreferenceHelper
) : BaseViewModel() {

    fun getTodayWeather(lat: Double, lot: Double) = getDataResult {
        weatherRepository.getTodayWeather(lat, lot, preferenceHelper.get(Constants.UNITS))
    }

    fun get5DaysForecast(lat: Double, lot: Double) = getDataResult {
        weatherRepository.getForecast(lat, lot, preferenceHelper.get(Constants.UNITS))
    }

    fun saveUnits(units: String) {
        preferenceHelper.save(Constants.UNITS, units)
    }

    fun getUnits(): String? = preferenceHelper.get(Constants.UNITS, Constants.IMPERIAL)

    fun getUnitsCode(): String = getUnits().let {
        if (it == Constants.METRIC) Constants.SYS_CELSIUS else Constants.SYS_FAHRENHEIT
    }
}