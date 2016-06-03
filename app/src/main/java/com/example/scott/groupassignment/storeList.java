package com.example.scott.groupassignment;


import com.google.android.gms.identity.intents.Address;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Scott on 5/5/2016.
 * For CSCI342 Assignment 2
 */

public class storeList {

    //Restaurant ID
    private int id;

    //Name of Restaurant
    private String name;

    private double latitude;
    private double longitude;
    private String address;

    //Distance Away From Restaurant
    private Double distance;

    //Site of Restaurant
    private String website;

    //Rating
    private double rating;

    public storeList(int id, String name, double distance, String website, double rating, double latitude, double longitude)
    {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.website = website;
        this.rating = rating;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public storeList() {
        id = 999;
        name = "Jacko's Burgers'";
        distance = 999.9;
        website = "no website listed";
        rating = 0;
        latitude = 0;
        longitude = 0;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Double getDistance() {
        return distance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getRating() {
        return rating;
    }
}