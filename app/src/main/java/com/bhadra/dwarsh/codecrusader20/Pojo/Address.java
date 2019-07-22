package com.bhadra.dwarsh.codecrusader20.Pojo;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("street")
    String street;
    @SerializedName("suite")
    String suite;
    @SerializedName("city")
    String city;
    @SerializedName("zipcode")
    String zipcode;
    @SerializedName("geo")
    Geo geo;
    public Address() {}
    public Address(String street,String suite,String city,String zipcode,Geo geo)
    {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Geo getGeo() {
        return geo;
    }
}
