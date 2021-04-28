package com.example.weatherApp.controllers;

import com.example.weatherApp.dto.WeatherDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Weather")
public interface WeatherController {

    @ApiOperation(value = "Weather", notes = "Endpoint to get a the weather of the city provided", response = WeatherDTO.class)
    WeatherDTO getMessage(String cityName);


}
