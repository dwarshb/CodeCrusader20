package com.bhadra.dwarsh.codecrusader20.Pojo;

import com.google.gson.annotations.SerializedName;

public class Geo {
    @SerializedName("lat")
    double lat;
    @SerializedName("lng")
    double lng;
    public Geo() {}
    public Geo(double lat,double lng)
    {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
