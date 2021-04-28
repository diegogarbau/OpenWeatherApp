package com.example.weatherApp.dto;

import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.Digits;

@JsonRootName(value = "Weather")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "condition", "temperature", "windSpeed" })
public class WeatherDTO {

    @JsonIgnore
    private String cityName;

    private String condition;
    @Digits(integer=3, fraction=2)
    private double temperature;

    @JsonProperty("wind_speed")
    @Digits(integer=3, fraction=2)
    private double windSpeed;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "WeatherDTO{ " +
                "cityName= '" + cityName + '\'' +
                ", condition= '" + condition + '\'' +
                ", temperature= " + temperature +"º"+
                ", windSpeed= " + windSpeed +" m/s"+
                '}';
    }

    public static class Builder{
        private String cityName;
        private String condition;
        private double temperature;
        private double windSpeed;

        public Builder setCityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public Builder setCondition(String condition) {
            this.condition = condition;
            return this;
        }
        public Builder setTemperature(double temperature) {
            this.temperature = temperature;
            return this;

        }
        public Builder setWindSpeed(double wind_speed) {
            this.windSpeed = wind_speed;
            return this;
        }

        public WeatherDTO build(){
            WeatherDTO weatherDTO = new WeatherDTO();
            weatherDTO.setCityName(cityName);
            weatherDTO.setCondition(condition);
            weatherDTO.setTemperature(temperature);
            weatherDTO.setWindSpeed(windSpeed);
            return weatherDTO;
        }
    }
}
