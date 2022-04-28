package com.example.weatherapp.model

import android.os.Parcel
import android.os.Parcelable

data class LocationWeatherDataRequest(
    val location: String,
    val humidityParameter: Boolean,
    val pressureParameter: Boolean,
    val speedOfWindParameter: Boolean) : Parcelable {

    private constructor(source: Parcel) : this(
        source.readString() as String,
        source.readValue(null) as Boolean,
        source.readValue(null) as Boolean,
        source.readValue(null) as Boolean
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(location)
        dest.writeValue(humidityParameter);
        dest.writeValue(pressureParameter);
        dest.writeValue(speedOfWindParameter);
    }

    companion object CREATOR : Parcelable.Creator<LocationWeatherDataRequest> {
        override fun createFromParcel(source : Parcel): LocationWeatherDataRequest {
            return LocationWeatherDataRequest(source);
        }
        override fun newArray(size: Int): Array<LocationWeatherDataRequest?> {
            return arrayOfNulls(size)
        }
    }

}