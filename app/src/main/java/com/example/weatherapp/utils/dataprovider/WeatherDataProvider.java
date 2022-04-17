package com.example.weatherapp.utils.dataprovider;

import com.example.weatherapp.utils.dataprovider.exceptions.EmptyRequestException;
import com.example.weatherapp.utils.dataprovider.exceptions.NoSuchLocationException;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataProvider {

    private static final String TEMPERATURE_TAG     = "TEMPERATURE";
    private static final String HUMIDITY_TAG        = "HUMIDITY";
    private static final String PRESSURE_TAG        = "PRESSURE";
    private static final String SPEED_OF_WIND_TAG   = "SPEED_OF_WIND";

    public static JSONObject getData(String city)
            throws EmptyRequestException, NoSuchLocationException, JSONException {

        if (city == null || city.isEmpty()) {
            throw new EmptyRequestException();
        }

        JSONObject resultJSON = new JSONObject();

        if (city.equalsIgnoreCase("moscow")){
            return resultJSON
                    .put(TEMPERATURE_TAG, -15.7)
                    .put(HUMIDITY_TAG, 80)
                    .put(PRESSURE_TAG, 765)
                    .put(SPEED_OF_WIND_TAG, 4);
        }

        if (city.equalsIgnoreCase("london")){
            return resultJSON
                    .put(TEMPERATURE_TAG, -5.0)
                    .put(HUMIDITY_TAG, 69)
                    .put(PRESSURE_TAG, 740)
                    .put(SPEED_OF_WIND_TAG, 2);
        }

        throw new NoSuchLocationException();

    }

    public static String getTemperatureTag() {
        return TEMPERATURE_TAG;
    }

    public static String getHumidityTag() {
        return HUMIDITY_TAG;
    }

    public static String getPressureTag() {
        return PRESSURE_TAG;
    }

    public static String getSpeedOfWindTag() {
        return SPEED_OF_WIND_TAG;
    }

}
