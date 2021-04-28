package com.example.weatherApp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rain {
    @JsonProperty("1h")
    private double _1h;

    @JsonProperty("3h")
    private double _3h;

    public Rain() {
    }

    public void set_1h(double _1h) {
        this._1h = _1h;
    }

    public void set_3h(double _3h) {
        this._3h = _3h;
    }
}
