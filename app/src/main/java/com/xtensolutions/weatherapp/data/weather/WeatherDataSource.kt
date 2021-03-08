package com.xtensolutions.weatherapp.data.weather

import com.xtensolutions.weatherapp.network.WeatherClient
import com.xtensolutions.weatherapp.room.dao.BookmarkCityDao
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import javax.inject.Inject


/**
 * Created by Vaghela Mithun R. on 17/02/21.
 * vaghela.mithun@gmail.com
 */
class WeatherDataSource @Inject constructor(private val weatherClient: WeatherClient) {

    suspend fun getTodayWeather(lat: Double, lon: Double, key: String, units: String) =
        weatherClient.getTodayWeather(lat, lon, key, units)

    suspend fun getForecast(lat: Double, lon: Double, key: String, units: String) =
        weatherClient.get5DaysForecastWeather(lat, lon, key, units)

}