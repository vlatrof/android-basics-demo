package com.example.weatherapp.utils.dataprovider

import com.example.weatherapp.model.LocationWeatherData
import com.example.weatherapp.utils.dataprovider.exceptions.EmptyRequestException
import com.example.weatherapp.utils.dataprovider.exceptions.NoSuchLocationException

class WeatherDataProvider {

    companion object {

        fun getData(location : String) : LocationWeatherData {

            if (location.isEmpty())
                throw EmptyRequestException()

            if (location.equals("moscow", true))
                return LocationWeatherData(location,
                    -25.0,
                    50.0,
                    740.0,
                    1.0
                )

            if (location.equals("london", true))
                return LocationWeatherData(location,
                    -15.7,
                    80.0,
                    765.0,
                    4.0
                )

            throw NoSuchLocationException();

        }

    }

}