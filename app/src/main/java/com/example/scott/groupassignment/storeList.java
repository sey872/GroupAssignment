package com.example.scott.groupassignment;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Scott on 5/5/2016.
 * For CSCI342 Assignment 2
 */
public class storeList {

    //Restaurant ID
    private int id;

    //Name of Restaurant
    private String name;

    //Distance Away From Restaurant
    private Double distance;

    public storeList(int id, String name, double distance)
    {
        this.id = id;
        this.name = name;
        this.distance = distance;
    }

    public storeList() {
        id = 999;
        name = "Jacko's Burgers'";
        distance = 999.9;
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

    public Double getDistance() {
        return distance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}