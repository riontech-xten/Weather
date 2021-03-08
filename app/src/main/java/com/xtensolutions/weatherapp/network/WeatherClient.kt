package com.xtensolutions.weatherapp.network

import com.xtensolutions.weatherapp.data.model.ForecastData
import com.xtensolutions.weatherapp.data.model.Weather
import com.xtensolutions.weatherapp.data.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Vaghela Mithun R. on 17/02/21.
 * vaghela.mithun@gmail.com
 */

interface WeatherClient {

    @GET("weather")
    suspend fun getTodayWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appId") apiKey: String,
        @Query("units") units: String
    ): Response<WeatherData>

    @GET("forecast")
    suspend fun get5DaysForecastWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appId") apiKey: String,
        @Query("units") units: String
    ): Response<ForecastData>

//    http://api.openweathermap.org/data/2.5/weather?lat=21.6422&lon=69.6093&appid=7c6bdcef0221d82e22a242700e57d080
}