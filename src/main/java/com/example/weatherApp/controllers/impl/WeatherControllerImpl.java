package com.example.weatherApp.controllers.impl;

import com.example.weatherApp.controllers.WeatherController;
import com.example.weatherApp.dto.WeatherDTO;
import com.example.weatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherControllerImpl implements WeatherController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherControllerImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Override
    public WeatherDTO getMessage(String cityName) {
        return weatherService.getWeather(cityName);
    }
}
