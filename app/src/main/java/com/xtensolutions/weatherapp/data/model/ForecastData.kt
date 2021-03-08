package com.xtensolutions.weatherapp.data.model

data class ForecastData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherData>,
    val message: Int
)