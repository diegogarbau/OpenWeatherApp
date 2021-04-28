package com.example.weatherApp.dto.mapper;

import com.example.weatherApp.dto.WeatherDTO;
import com.example.weatherApp.entity.Root;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RootToDto {
    private RootToDto() {
    }

    public static WeatherDTO map (Root root){

        return new WeatherDTO.Builder()
                .setCityName(root.getName())
                .setCondition(root.getWeather().get(0).getDescription())
                .setTemperature(round(root.getMain().getTemp()))
                .setWindSpeed(convertAndRound(root.getWind().getSpeed()))
                .build();
    }
    private static double convertAndRound(double speed) {
        double ratio = 3.60;
        BigDecimal bigDecimal = new BigDecimal(Double.toString(speed*ratio));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.CEILING);
        return bigDecimal.doubleValue();
    }
    private static double round(double temp) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(temp));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.CEILING);
        return bigDecimal.doubleValue();
    }

}
