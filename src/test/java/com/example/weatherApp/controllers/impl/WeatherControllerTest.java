package com.example.weatherApp.controllers.impl;

import com.example.weatherApp.controllers.WeatherController;
import com.example.weatherApp.dto.WeatherDTO;
import com.example.weatherApp.exceptions.NotFoundException;
import com.example.weatherApp.service.WeatherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@TestPropertySource(locations = "/test.properties")
public class WeatherControllerTest {

    @Mock
    private WeatherService weatherServiceTest;

    private WeatherController weatherControllerTest;

    @Before
    public void setUp() {
        weatherControllerTest = new WeatherControllerImpl(weatherServiceTest);
    }

    @Test
    public void ServiceWorksTest() {
        String cityName = "London";
        String condition = "overcast clouds";
        WeatherDTO weatherDTO = new WeatherDTO.Builder().setCityName(cityName).setCondition(condition).build();
        Mockito.doReturn(weatherDTO).when(weatherServiceTest).getWeather(any());

        WeatherDTO response = weatherControllerTest.getMessage(cityName);

        Assert.assertEquals("City names does not match", cityName, response.getCityName());
        Assert.assertEquals("City names does not match", condition, response.getCondition());
    }

    @Test(expected = NotFoundException.class)
    public void getWeatherGetNotFoundExceptionTest() {
        Mockito.doThrow(NotFoundException.class).when(weatherServiceTest).getWeather(any());
        weatherControllerTest.getMessage("London");
    }


}

