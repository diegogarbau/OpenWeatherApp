package com.example.weatherApp.entity;

public class Sys{
    private int type;
    private int id;
    private String country;
    private int sunrise;
    private int sunset;

    public Sys() {
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }
}
