package com.xtensolutions.weatherapp.data.model

data class Wind(
    val deg: Int,
    val speed: Double
) {
    fun speedWithUnit(): String {
        return "${speed}km/h"
    }
}