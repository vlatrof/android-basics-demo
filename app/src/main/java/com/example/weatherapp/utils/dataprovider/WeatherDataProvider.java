package com.example.weatherapp.utils.dataprovider;

import com.example.weatherapp.model.LocationWeatherData;
import com.example.weatherapp.utils.dataprovider.exceptions.EmptyRequestException;
import com.example.weatherapp.utils.dataprovider.exceptions.NoSuchLocationException;

public class WeatherDataProvider {

    public static LocationWeatherData getData(String location)
            throws EmptyRequestException, NoSuchLocationException {

        if (location == null || location.isEmpty()) {
            throw new EmptyRequestException();
        }

        if (location.equalsIgnoreCase("moscow")) {

            double temperature = -25;
            double humidity = 50;
            double pressure = 740;
            double speedOfWind = 1;

            return new LocationWeatherData(location, temperature, humidity, pressure, speedOfWind);

        }

        if (location.equalsIgnoreCase("london")) {

            double temperature = -15.7;
            double humidity = 80;
            double pressure = 765;
            double speedOfWind = 4;

            return new LocationWeatherData(location, temperature, humidity, pressure, speedOfWind);

        }

        throw new NoSuchLocationException();

    }

}
