package com.example.weatherApp.controllers.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class WeatherControllerIntegrationTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getWeatherLondonWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/").param("cityName", "London").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.condition").exists())
                .andExpect(jsonPath("$.temperature").isNumber())
                .andExpect(jsonPath("$.wind_speed").isNumber());
    }

    @Test
    public void getWeatherMoscuWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/").param("cityName", "Moscu").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.condition").exists())
                .andExpect(jsonPath("$.temperature").isNumber())
                .andExpect(jsonPath("$.wind_speed").isNumber());
    }

    @Test
    public void getWeatherParisWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/").param("cityName", "Paris").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.condition").exists())
                .andExpect(jsonPath("$.temperature").isNumber())
                .andExpect(jsonPath("$.wind_speed").isNumber());
    }

    @Test
    public void getWeatherGrytvikenWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/").param("cityName", "Grytviken").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.condition").exists())
                .andExpect(jsonPath("$.temperature").isNumber())
                .andExpect(jsonPath("$.wind_speed").isNumber());
    }


    @Test
    public void getWeatherMosedisWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/").param("cityName", "Mosedis").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.condition").exists())
                .andExpect(jsonPath("$.temperature").isNumber())
                .andExpect(jsonPath("$.wind_speed").isNumber());
    }

    @Test
    public void getWeatherButNotFoundTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/").param("cityName", "FAKE").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }


}
