package com.xtensolutions.weatherapp.data.weather

import com.xtensolutions.weatherapp.BuildConfig
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import javax.inject.Inject


/**
 * Created by Vaghela Mithun R. on 17/02/21.
 * vaghela.mithun@gmail.com
 */
class WeatherRepository @Inject constructor(private val weatherDataSource: WeatherDataSource) {
    suspend fun getTodayWeather(lat: Double, lon: Double, units: String) =
        weatherDataSource.getTodayWeather(lat, lon, BuildConfig.API_KEY, units)

    suspend fun getForecast(lat: Double, lon: Double, units: String) =
        weatherDataSource.getForecast(lat, lon, BuildConfig.API_KEY, units)

}