package com.pojo;

import java.util.ArrayList;
import java.util.List;

public class GeoLocation {
    double latitude;
    double longitude;
    double prepTime;
    String name;
    List<GeoLocation> adjacentGeoLocations = new ArrayList<GeoLocation>();

    public GeoLocation(double latitude, double longitude, List<GeoLocation> adjacentGeoLocations) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.adjacentGeoLocations = adjacentGeoLocations;
    }

    public GeoLocation(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public GeoLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public GeoLocation(double latitude, double longitude, Double prepTime,String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.prepTime = prepTime;
        this.name = name;
    }



    public Double getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(double prepTime) {
        this.prepTime = prepTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<GeoLocation> getAdjacentGeoLocations() {
        return adjacentGeoLocations;
    }

    public void setAdjacentGeoLocations(List<GeoLocation> adjacentGeoLocations) {
        this.adjacentGeoLocations = adjacentGeoLocations;
    }
}
