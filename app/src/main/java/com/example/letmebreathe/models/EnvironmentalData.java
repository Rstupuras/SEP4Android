package com.example.letmebreathe.models;

import java.io.Serializable;
import java.text.DecimalFormat;

import static java.lang.Math.round;

public class EnvironmentalData implements Serializable {


    private String location;
    private double CO2;
    private double temperature;
    private String time;

    private static DecimalFormat df2 = new DecimalFormat(".##");

    public EnvironmentalData() {

    }

    public EnvironmentalData(String location, double CO2, double temperature, String time) {
        this.location = location;
        this.CO2 = CO2;
        this.temperature = temperature;
        this.time = time;
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


    public String toString() {
        String s = "";
        s += "Temperature: " + df2.format(temperature) + " C \t CO2: " + df2.format(CO2) + "\n";
        return s;
    }

}
