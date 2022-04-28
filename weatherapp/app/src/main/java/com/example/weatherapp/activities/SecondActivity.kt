package com.example.weatherapp.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.model.LocationWeatherData
import com.example.weatherapp.model.LocationWeatherDataRequest
import com.example.weatherapp.utils.dataprovider.WeatherDataProvider
import com.example.weatherapp.utils.dataprovider.exceptions.EmptyRequestException
import com.example.weatherapp.utils.dataprovider.exceptions.NoSuchLocationException

class SecondActivity : AppCompatActivity() {

    private lateinit var locationTV: TextView
    private lateinit var temperatureInfoTV: TextView
    private lateinit var humidityInfoTV: TextView
    private lateinit var pressureInfoTV: TextView
    private lateinit var speedOfWindInfoTV: TextView
    private lateinit var errorMessageTV: TextView

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initViews()

        try {

            if (intent == null)
                throw Exception("Null intent!")

            if (intent.extras == null)
                throw Exception("Null intent extras!")

        } catch (e: Exception) {

            e.printStackTrace()
            renderErrorMessage(getString(R.string.second_activity_getting_data_error_shown_text))
            return

        }

        val request: LocationWeatherDataRequest? = intent.getParcelableExtra(
            LocationWeatherDataRequest::class.java.canonicalName
        )

        try {

            val data = loadWeatherData(request)

            renderWeatherData(
                data,
                request!!.humidityParameter,
                request!!.pressureParameter,
                request!!.speedOfWindParameter
            )

        } catch (e: EmptyRequestException) {

            e.printStackTrace()
            renderErrorMessage(getString(R.string.second_activity_getting_data_error_shown_text))

        } catch (e: NoSuchLocationException) {

            e.printStackTrace()
            renderErrorMessage(
                getString(R.string.second_activity_no_such_location_error_shown_text) + " " + request!!.location
            )

        }

    }

    private fun initViews() {

        locationTV = findViewById(R.id.tv_location)
        temperatureInfoTV = findViewById(R.id.tv_temperature)
        humidityInfoTV = findViewById(R.id.tv_humidity)
        pressureInfoTV = findViewById(R.id.tv_pressure)
        speedOfWindInfoTV = findViewById(R.id.tv_speed_of_wind)
        errorMessageTV = findViewById(R.id.tv_bad_req_message)

    }

    private fun loadWeatherData(request: LocationWeatherDataRequest?): LocationWeatherData {

        return WeatherDataProvider.getData(request!!.location)

    }

    private fun renderWeatherData(
        data: LocationWeatherData,
        isHumidityRequested: Boolean,
        isPressureRequested: Boolean,
        isSpeedOfWindRequested: Boolean
    ) {

        locationTV.text = data.location
        locationTV.visibility = View.VISIBLE
        val temperatureStr = String.format(
            "%s %.0f", getString(
                R.string.second_activity_show_temperature_text
            ),
            data.temperature
        )

        temperatureInfoTV.text = temperatureStr
        temperatureInfoTV.visibility = View.VISIBLE

        if (isHumidityRequested) {
            val str = String.format(
                "%s %.0f",
                getString(R.string.second_activity_show_humidity_text),
                data.humidity
            )
            humidityInfoTV.text = str
            humidityInfoTV.visibility = View.VISIBLE
        }

        if (isPressureRequested) {
            val str = String.format(
                "%s %.0f",
                getString(R.string.second_activity_show_pressure_text),
                data.pressure
            )
            pressureInfoTV.text = str
            pressureInfoTV.visibility = View.VISIBLE
        }

        if (isSpeedOfWindRequested) {
            val str = String.format(
                "%s %.0f",
                getString(R.string.second_activity_show_speed_of_wind_text),
                data.speedOfWind
            )
            speedOfWindInfoTV.text = str
            speedOfWindInfoTV.visibility = View.VISIBLE
        }

    }

    private fun renderErrorMessage(msg: String) {

        temperatureInfoTV.visibility = View.GONE
        humidityInfoTV.visibility = View.GONE
        pressureInfoTV.visibility = View.GONE
        speedOfWindInfoTV.visibility = View.GONE

        errorMessageTV.text = msg
        errorMessageTV.visibility = View.VISIBLE

    }

}