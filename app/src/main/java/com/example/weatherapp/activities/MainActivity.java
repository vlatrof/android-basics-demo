package com.example.weatherapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.model.LocationWeatherDataRequest;
import com.example.weatherapp.utils.dataprovider.exceptions.EmptyRequestException;
import com.example.weatherapp.R;

public class MainActivity extends AppCompatActivity {

    private static final String LOCATION_INPUT_TAG = "LOCATION_INPUT";
    private static final String SHOW_HUMIDITY_TAG = "SHOW_HUMIDITY";
    private static final String SHOW_PRESSURE_TAG = "SHOW_PRESSURE";
    private static final String SHOW_SPEED_OF_WIND_TAG = "SHOW_SPEED_OF_WIND";

    private ScrollView scrollView;
    private EditText locationInput;
    private Button showWeatherButton;
    private CheckBox checkBoxShowHumidity;
    private CheckBox checkBoxShowPressure;
    private CheckBox checkBoxShowSpeedOfWind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        showWeatherButton.setOnClickListener(v -> onClickShowWeatherButton());
        scrollView.setOnTouchListener((v, event) -> onTouchScrollView());

    }

    @Override
    protected void onStart() {

        super.onStart();
        locationInput.getText().clear();
        locationInput.clearFocus();

    }

    private void initViews() {

        scrollView = findViewById(R.id.main_activity_scroll_view);
        locationInput = findViewById(R.id.et_location_input);
        showWeatherButton = findViewById(R.id.btn_show_weather);
        checkBoxShowHumidity = findViewById(R.id.cb_show_humidity);
        checkBoxShowPressure = findViewById(R.id.cb_show_pressure);
        checkBoxShowSpeedOfWind = findViewById(R.id.cb_show_speed_of_wind);

    }

    private void onClickShowWeatherButton() {

        try {

            if (locationInput.getText().toString().isEmpty())
                throw new EmptyRequestException();

            LocationWeatherDataRequest request = new LocationWeatherDataRequest(
                    locationInput.getText().toString(),
                    checkBoxShowHumidity.isChecked(),
                    checkBoxShowPressure.isChecked(),
                    checkBoxShowSpeedOfWind.isChecked()
            );

            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(LocationWeatherDataRequest.class.getCanonicalName(), request);
            startActivity(intent);

        } catch (EmptyRequestException e) {

            e.printStackTrace();
            Toast.makeText(this, getString(R.string.main_activity_empty_req_toast_text),
                    Toast.LENGTH_LONG).show();

        }

    }

    private boolean onTouchScrollView() {

        locationInput.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(locationInput.getWindowToken(), 0);
        return false;

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putString(LOCATION_INPUT_TAG, locationInput.getText().toString());
        outState.putBoolean(SHOW_HUMIDITY_TAG, checkBoxShowHumidity.isChecked());
        outState.putBoolean(SHOW_PRESSURE_TAG, checkBoxShowPressure.isChecked());
        outState.putBoolean(SHOW_SPEED_OF_WIND_TAG, checkBoxShowSpeedOfWind.isChecked());

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        locationInput.setText(savedInstanceState.getString(LOCATION_INPUT_TAG));
        checkBoxShowHumidity.setChecked(savedInstanceState.getBoolean(SHOW_HUMIDITY_TAG));
        checkBoxShowPressure.setChecked(savedInstanceState.getBoolean(SHOW_PRESSURE_TAG));
        checkBoxShowSpeedOfWind.setChecked(savedInstanceState.getBoolean(SHOW_SPEED_OF_WIND_TAG));

    }

}
