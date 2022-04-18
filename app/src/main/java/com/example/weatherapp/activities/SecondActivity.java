package com.example.weatherapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.weatherapp.utils.dataprovider.WeatherDataProvider;
import com.example.weatherapp.utils.dataprovider.exceptions.EmptyRequestException;
import com.example.weatherapp.utils.dataprovider.exceptions.NoSuchLocationException;
import com.example.weatherapp.R;

public class SecondActivity extends AppCompatActivity {

    private static final String LOCATION_INPUT_TAG      = "LOCATION_INPUT";
    private static final String SHOW_HUMIDITY_TAG       = "SHOW_HUMIDITY";
    private static final String SHOW_PRESSURE_TAG       = "SHOW_PRESSURE";
    private static final String SHOW_SPEED_OF_WIND_TAG  = "SHOW_SPEED_OF_WIND";

    TextView locationTV;
    TextView temperatureInfoTV;
    TextView humidityInfoTV;
    TextView pressureInfoTV;
    TextView speedOfWindInfoTV;
    TextView badReqMessageTV;
    String targetLocation;

    @Override public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        loadWeatherData();

    }

    private void initViews() {

        locationTV        = findViewById(R.id.location_title);
        temperatureInfoTV = findViewById(R.id.weather_info_temperature);
        humidityInfoTV    = findViewById(R.id.weather_info_humidity);
        pressureInfoTV    = findViewById(R.id.weather_info_pressure);
        speedOfWindInfoTV = findViewById(R.id.weather_info_speed_of_wind);
        badReqMessageTV   = findViewById(R.id.bad_req_message);

    }

    private void loadWeatherData() {

        Bundle extras = getIntent().getExtras();

        try {

            targetLocation = extras.getString(LOCATION_INPUT_TAG);

            if (targetLocation == null)
                throw new EmptyRequestException();

            JSONObject response = WeatherDataProvider.getData(targetLocation);

            locationTV.setText(targetLocation);
            locationTV.setVisibility(View.VISIBLE);

            String temperatureStr = String.format("%s %s",
                    getString(R.string.second_activity_show_temperature_text),
                    response.get(WeatherDataProvider.getTemperatureTag()));
            temperatureInfoTV.setText(temperatureStr);
            temperatureInfoTV.setVisibility(View.VISIBLE);

            if (extras.getBoolean(SHOW_HUMIDITY_TAG)) {

                String str = String.format("%s %s",
                        getString(R.string.second_activity_show_humidity_text),
                        response.get(WeatherDataProvider.getHumidityTag()));
                humidityInfoTV.setText(str);
                humidityInfoTV.setVisibility(View.VISIBLE);

            }

            if (extras.getBoolean(SHOW_PRESSURE_TAG)) {

                String str = String.format("%s %s",
                        getString(R.string.second_activity_show_pressure_text),
                        response.get(WeatherDataProvider.getPressureTag()));
                pressureInfoTV.setText(str);
                pressureInfoTV.setVisibility(View.VISIBLE);

            }

            if (extras.getBoolean(SHOW_SPEED_OF_WIND_TAG)) {

                String str = String.format("%s %s",
                        getString(R.string.second_activity_show_speed_of_wind_text),
                        response.get(WeatherDataProvider.getSpeedOfWindTag()));
                speedOfWindInfoTV.setText(str);
                speedOfWindInfoTV.setVisibility(View.VISIBLE);

            }

        } catch (EmptyRequestException | JSONException e) {

            temperatureInfoTV.setVisibility(View.GONE);
            humidityInfoTV   .setVisibility(View.GONE);
            pressureInfoTV   .setVisibility(View.GONE);
            speedOfWindInfoTV.setVisibility(View.GONE);

            badReqMessageTV.setText(getString(R.string.second_activity_bad_req_exception_shown_text));
            badReqMessageTV.setVisibility(View.GONE);

        } catch (NoSuchLocationException e) {

            temperatureInfoTV.setVisibility(View.GONE);
            humidityInfoTV   .setVisibility(View.GONE);
            pressureInfoTV   .setVisibility(View.GONE);
            speedOfWindInfoTV.setVisibility(View.GONE);

            String str = String.format("%s %s",
                    getString(R.string.second_activity_no_such_location_exception_shown_text),
                    targetLocation);

            badReqMessageTV.setText(str);
            badReqMessageTV.setVisibility(View.VISIBLE);

        }

    }

}
