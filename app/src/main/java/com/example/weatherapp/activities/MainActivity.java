package com.example.weatherapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.utils.dataprovider.exceptions.EmptyRequestException;
import com.example.weatherapp.R;

public class MainActivity extends AppCompatActivity {

    private static final String LOCATION_INPUT_TAG     = "LOCATION_INPUT";
    private static final String SHOW_HUMIDITY_TAG      = "SHOW_HUMIDITY";
    private static final String SHOW_PRESSURE_TAG      = "SHOW_PRESSURE";
    private static final String SHOW_SPEED_OF_WIND_TAG = "SHOW_SPEED_OF_WIND";

    EditText locationInput;
    Button showWeatherButton;
    CheckBox checkBoxShowHumidity;
    CheckBox checkBoxShowPressure;
    CheckBox checkBoxShowSpeedOfWind;

    @Override protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        showWeatherButton.setOnClickListener(v -> onClickShowWeatherButton());

    }

    @Override protected void onStart() {

        super.onStart();
        locationInput.getText().clear();
        locationInput.clearFocus();

    }

    private void initViews() {

        locationInput           = findViewById(R.id.location_input);
        showWeatherButton       = findViewById(R.id.show_weather_button);
        checkBoxShowHumidity    = findViewById(R.id.checkBoxShowHumidity);
        checkBoxShowPressure    = findViewById(R.id.checkBoxShowPressure);
        checkBoxShowSpeedOfWind = findViewById(R.id.checkBoxShowSpeedOfWind);

    }

    private void onClickShowWeatherButton(){

        try {

            if (locationInput.getText().toString().isEmpty())
                throw new EmptyRequestException();

            Intent intent = new Intent(this, SecondActivity.class)
                    .putExtra(LOCATION_INPUT_TAG, locationInput.getText().toString())
                    .putExtra(SHOW_HUMIDITY_TAG, checkBoxShowHumidity.isChecked())
                    .putExtra(SHOW_PRESSURE_TAG, checkBoxShowPressure.isChecked())
                    .putExtra(SHOW_SPEED_OF_WIND_TAG, checkBoxShowSpeedOfWind.isChecked());

            startActivity(intent);

        } catch (EmptyRequestException e) {

            e.printStackTrace();
            Toast.makeText(this, getString(R.string.main_activity_empty_req_toast_text),
                    Toast.LENGTH_LONG).show();

        }

    }

    @Override protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(LOCATION_INPUT_TAG, locationInput.getText().toString());
        outState.putBoolean(SHOW_HUMIDITY_TAG, checkBoxShowHumidity.isChecked());
        outState.putBoolean(SHOW_PRESSURE_TAG, checkBoxShowPressure.isChecked());
        outState.putBoolean(SHOW_SPEED_OF_WIND_TAG, checkBoxShowSpeedOfWind.isChecked());

    }

    @Override protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        locationInput.setText(savedInstanceState.getString(LOCATION_INPUT_TAG));
        checkBoxShowHumidity.setChecked(savedInstanceState.getBoolean(SHOW_HUMIDITY_TAG));
        checkBoxShowPressure.setChecked(savedInstanceState.getBoolean(SHOW_PRESSURE_TAG));
        checkBoxShowSpeedOfWind.setChecked(savedInstanceState.getBoolean(SHOW_SPEED_OF_WIND_TAG));

    }

}
