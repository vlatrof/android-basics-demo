package com.example.weatherapp.model

data class LocationWeatherData(
    val location: String = "",
    val temperature: Double = .0,
    val humidity: Double = .0,
    val pressure: Double = .0,
    val speedOfWind: Double =.0
)