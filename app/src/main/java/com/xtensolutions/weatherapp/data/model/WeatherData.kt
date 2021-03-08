package com.xtensolutions.weatherapp.data.model

import com.google.gson.annotations.SerializedName
import com.xtensolutions.weatherapp.extension.convertToDay
import com.xtensolutions.weatherapp.extension.convertToTime

data class WeatherData(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind,
    @SerializedName("dt_txt")
    val dtTxt: String
) {
    fun dayCloudStatus(): String {
        return "${dtTxt.convertToDay()}- ${weather.get(0).main}"
    }

    fun hourlyTime(): String {
        return dtTxt.convertToTime()
    }
}