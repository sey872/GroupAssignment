package com.example.scott.myapplication;

/**
 * Created by Scott on 10/12/2016.
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

    public storeList(int id, String name, String address, double distance, String website, double rating, double latitude, double longitude)
    {
        this.id = id;
        this.address = address;
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
        address = "Jacko's place";
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

    public void setAddress(String address) {
        this.address = address;
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

    public String getAddress() {
        return address;
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