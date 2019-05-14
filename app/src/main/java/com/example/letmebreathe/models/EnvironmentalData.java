package com.example.letmebreathe.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DecimalFormat;

import static java.lang.Math.round;

public class EnvironmentalData implements Serializable {

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("co2")
    private double CO2;
    @SerializedName("temperature")
    private double temperature;
    @SerializedName("time")
    private String time;


    @SerializedName("date")
    private String date;


    private static DecimalFormat df2 = new DecimalFormat(".##");

    public EnvironmentalData() {

    }

    public EnvironmentalData(String location, double CO2, double temperature, String time, String date) {
        this.location = location;
        this.CO2 = CO2;
        this.temperature = temperature;
        this.time = time;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getCO2() {
        return round(CO2);
    }

    public void setCO2(double CO2) {
        this.CO2 = CO2;
    }

    public double getTemperature() {
        return round(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String toString() {
        String s = "";
        s += "Temperature: " + df2.format(temperature) + " C \t CO2: " + df2.format(CO2) + "\n";
        return s;
    }

}
