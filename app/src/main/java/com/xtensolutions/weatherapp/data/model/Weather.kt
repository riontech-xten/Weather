package com.xtensolutions.weatherapp.data.model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) {
    fun iconUrl(): String = "http://openweathermap.org/img/wn/$icon.png"
    fun today(): String = "Today - $main"
}