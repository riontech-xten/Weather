package com.xtensolutions.weatherapp.data.model

import com.xtensolutions.weatherapp.extension.toTime
import java.util.*

data class Sys(
    val country: String,
    val sunrise: Long,
    val sunset: Long
) {
    fun sunriseTime(): String {
        return "Sunrise ${sunrise.toTime()}"
    }

    fun sunsetTime(): String {
        return "Sunset ${sunset.toTime()}"
    }
}