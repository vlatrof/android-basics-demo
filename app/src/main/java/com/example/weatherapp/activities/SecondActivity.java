package com.example.weatherapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.model.LocationWeatherData;
import com.example.weatherapp.model.LocationWeatherDataRequest;
import com.example.weatherapp.utils.dataprovider.WeatherDataProvider;
import com.example.weatherapp.utils.dataprovider.exceptions.EmptyRequestException;
import com.example.weatherapp.utils.dataprovider.exceptions.NoSuchLocationException;
import com.example.weatherapp.R;

public class SecondActivity extends AppCompatActivity {

    private TextView locationTV;
    private TextView temperatureInfoTV;
    private TextView humidityInfoTV;
    private TextView pressureInfoTV;
    private TextView speedOfWindInfoTV;
    private TextView errorMessageTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();

        try {

            if (getIntent() == null)
                throw new NullPointerException("Null intent!");

            if (getIntent().getExtras() == null)
                throw new NullPointerException("Null intent extras!");

        } catch (NullPointerException e) {

            e.printStackTrace();
            renderErrorMessage(getString(R.string.second_activity_getting_data_error_shown_text));
            return;

        }

        LocationWeatherDataRequest request =
                getIntent().getParcelableExtra(LocationWeatherDataRequest.class.getCanonicalName());

        try {

            LocationWeatherData data = loadWeatherData(request);

            renderWeatherData(data,
                    request.isHumidityParameter(),
                    request.isPressureParameter(),
                    request.isSpeedOfWindParameter());

        } catch (EmptyRequestException e) {

            e.printStackTrace();
            renderErrorMessage(getString(R.string.second_activity_getting_data_error_shown_text));

        } catch (NoSuchLocationException e) {

            e.printStackTrace();
            renderErrorMessage(
                    getString(R.string.second_activity_no_such_location_error_shown_text)
                            + " " + request.getLocation());

        }

    }

    private void initViews() {

        locationTV = findViewById(R.id.tv_location);
        temperatureInfoTV = findViewById(R.id.tv_temperature);
        humidityInfoTV = findViewById(R.id.tv_humidity);
        pressureInfoTV = findViewById(R.id.tv_pressure);
        speedOfWindInfoTV = findViewById(R.id.tv_speed_of_wind);
        errorMessageTV = findViewById(R.id.tv_bad_req_message);

    }

    private LocationWeatherData loadWeatherData(LocationWeatherDataRequest request)
            throws EmptyRequestException, NoSuchLocationException {

        if (request.getLocation() == null)
            throw new EmptyRequestException();

        return WeatherDataProvider.getData(request.getLocation());

    }

    private void renderWeatherData(LocationWeatherData data,
                                   boolean isHumidityRequested,
                                   boolean isPressureRequested,
                                   boolean isSpeedOfWindRequested) {

        locationTV.setText(data.getLocation());
        locationTV.setVisibility(View.VISIBLE);

        String temperatureStr = String.format("%s %.0f", getString(
                R.string.second_activity_show_temperature_text),
                data.getTemperature());
        temperatureInfoTV.setText(temperatureStr);
        temperatureInfoTV.setVisibility(View.VISIBLE);

        if (isHumidityRequested) {
            String str = String.format("%s %.0f",
                    getString(R.string.second_activity_show_humidity_text),
                    data.getHumidity());
            humidityInfoTV.setText(str);
            humidityInfoTV.setVisibility(View.VISIBLE);
        }

        if (isPressureRequested) {
            String str = String.format("%s %.0f",
                    getString(R.string.second_activity_show_pressure_text),
                    data.getPressure());
            pressureInfoTV.setText(str);
            pressureInfoTV.setVisibility(View.VISIBLE);
        }

        if (isSpeedOfWindRequested) {
            String str = String.format("%s %.0f",
                    getString(R.string.second_activity_show_speed_of_wind_text),
                    data.getSpeedOfWind());
            speedOfWindInfoTV.setText(str);
            speedOfWindInfoTV.setVisibility(View.VISIBLE);
        }

    }

    private void renderErrorMessage(String msg) {

        temperatureInfoTV.setVisibility(View.GONE);
        humidityInfoTV.setVisibility(View.GONE);
        pressureInfoTV.setVisibility(View.GONE);
        speedOfWindInfoTV.setVisibility(View.GONE);

        errorMessageTV.setText(msg);
        errorMessageTV.setVisibility(View.VISIBLE);

    }

}



        

        