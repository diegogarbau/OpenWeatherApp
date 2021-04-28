package com.example.weatherApp.entity;

public class Weather{
    private int id;
    private String main;
    private String description;
    private String icon;

    public Weather() {
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
