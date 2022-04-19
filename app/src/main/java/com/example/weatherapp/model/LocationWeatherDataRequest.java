package com.example.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LocationWeatherDataRequest implements Parcelable {

    private final String    location;
    private final boolean   humidityParameter;
    private final boolean   pressureParameter;
    private final boolean   speedOfWindParameter;

    public LocationWeatherDataRequest(String location, boolean humidityParameter, boolean pressureParameter, boolean speedOfWindParameter) {
        this.location = location;
        this.humidityParameter = humidityParameter;
        this.pressureParameter = pressureParameter;
        this.speedOfWindParameter = speedOfWindParameter;
    }

    public String getLocation() {
        return location;
    }

    public boolean isHumidityParameter() {
        return humidityParameter;
    }

    public boolean isPressureParameter() {
        return pressureParameter;
    }

    public boolean isSpeedOfWindParameter() {
        return speedOfWindParameter;
    }

    public static final Creator<LocationWeatherDataRequest> CREATOR = new Creator<LocationWeatherDataRequest>() {

        @Override
        public LocationWeatherDataRequest createFromParcel(Parcel source) {

            String location             = source.readString();
            boolean humidityParameter   = source.readBoolean();
            boolean pressureParameter   = source.readBoolean();
            boolean speedOfWindParameter = source.readBoolean();

            return new LocationWeatherDataRequest(location,
                                                  humidityParameter,
                                                  pressureParameter,
                                                  speedOfWindParameter);
        }

        @Override
        public LocationWeatherDataRequest[] newArray(int size) {
            return new LocationWeatherDataRequest[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(location);
        dest.writeBoolean(humidityParameter);
        dest.writeBoolean(pressureParameter);
        dest.writeBoolean(speedOfWindParameter);
    }

}
