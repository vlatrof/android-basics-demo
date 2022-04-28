package com.example.weatherapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.model.LocationWeatherDataRequest
import com.example.weatherapp.utils.dataprovider.exceptions.EmptyRequestException


class MainActivity : AppCompatActivity() {

    val LOCATION_INPUT_TAG: String = "LOCATION_INPUT"
    val SHOW_HUMIDITY_TAG: String = "SHOW_HUMIDITY"
    val SHOW_PRESSURE_TAG: String = "SHOW_PRESSURE"
    val SHOW_SPEED_OF_WIND_TAG: String = "SHOW_SPEED_OF_WIND"

    private lateinit var scrollView: ScrollView
    private lateinit var locationInput: EditText
    private lateinit var showWeatherButton: Button
    private lateinit var checkBoxShowHumidity: CheckBox
    private lateinit var checkBoxShowPressure: CheckBox
    private lateinit var checkBoxShowSpeedOfWind: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        showWeatherButton.setOnClickListener { onClickShowWeatherButton() }
        scrollView.setOnTouchListener{ v, event -> onTouchScrollView()}

    }

    override fun onStart() {
        super.onStart()
        locationInput.clearFocus();
    }

    private fun initViews() {
        scrollView = findViewById(R.id.main_activity_scroll_view)
        locationInput = findViewById(R.id.et_location_input)
        showWeatherButton = findViewById(R.id.btn_show_weather)
        checkBoxShowHumidity = findViewById(R.id.cb_show_humidity)
        checkBoxShowPressure = findViewById(R.id.cb_show_pressure)
        checkBoxShowSpeedOfWind = findViewById(R.id.cb_show_speed_of_wind)
    }

    private fun onClickShowWeatherButton() {

        try {

            if (locationInput.text.toString().isEmpty())
                throw EmptyRequestException()

            val request = LocationWeatherDataRequest(
                locationInput.text.toString(),
                checkBoxShowHumidity.isChecked,
                checkBoxShowPressure.isChecked(),
                checkBoxShowSpeedOfWind.isChecked()
            )

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(LocationWeatherDataRequest::class.java.canonicalName, request)
            startActivity(intent)

        } catch (e: EmptyRequestException) {
            e.printStackTrace()
            Toast.makeText(
                this, getString(R.string.main_activity_empty_req_toast_text),
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun onTouchScrollView(): Boolean {
        locationInput.clearFocus()
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(locationInput.windowToken, 0)
        return false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LOCATION_INPUT_TAG, locationInput.text.toString())
        outState.putBoolean(SHOW_HUMIDITY_TAG, checkBoxShowHumidity.isChecked)
        outState.putBoolean(SHOW_PRESSURE_TAG, checkBoxShowPressure.isChecked)
        outState.putBoolean(SHOW_SPEED_OF_WIND_TAG, checkBoxShowSpeedOfWind.isChecked)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        locationInput.setText(savedInstanceState.getString(LOCATION_INPUT_TAG))
        checkBoxShowHumidity.isChecked = savedInstanceState.getBoolean(SHOW_HUMIDITY_TAG)
        checkBoxShowPressure.isChecked = savedInstanceState.getBoolean(SHOW_PRESSURE_TAG)
        checkBoxShowSpeedOfWind.isChecked = savedInstanceState.getBoolean(SHOW_SPEED_OF_WIND_TAG)
    }

}















