package com.example.weatherapp.model;

import java.util.Objects;

public class LocationWeatherData {

    private final String    location;
    private final double    temperature;
    private final double    humidity;
    private final double    pressure;
    private final double    speedOfWind;

    public LocationWeatherData(String location,
                               double temperature,
                               double humidity,
                               double pressure,
                               double speedOfWind) {

        this.location       = location;
        this.temperature    = temperature;
        this.humidity       = humidity;
        this.pressure       = pressure;
        this.speedOfWind    = speedOfWind;

    }

    public String getLocation() {
        return location;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSpeedOfWind() {
        return speedOfWind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationWeatherData that = (LocationWeatherData) o;
        return temperature == that.temperature && humidity == that.humidity && pressure == that.pressure && speedOfWind == that.speedOfWind && location.equals(that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, temperature, humidity, pressure, speedOfWind);
    }

    @Override
    public String toString() {
        return "LocationWeatherData{" +
                "location='" + location + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", speedOfWind=" + speedOfWind +
                '}';
    }

}

