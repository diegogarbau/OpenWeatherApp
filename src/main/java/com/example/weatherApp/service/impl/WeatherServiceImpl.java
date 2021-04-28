package com.example.weatherApp.service.impl;

import com.example.weatherApp.dto.mapper.RootToDto;
import com.example.weatherApp.dto.WeatherDTO;
import com.example.weatherApp.entity.Root;
import com.example.weatherApp.exceptions.*;
import com.example.weatherApp.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weathermap.uri}")
    private String uri;
    @Value("${weathermap.apikey}")
    private String apiKey;
    @Value("${weathermap.units}")
    private String units;

    private static final Logger LOGGER = Logger.getLogger(WeatherServiceImpl.class.getName());

    private HttpClient client = HttpClientBuilder.create()
            .setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .build())
            .build();

    public WeatherServiceImpl() {
    }

    public WeatherServiceImpl(HttpClient client) {
        this.client = client;
    }

    @Override
    public WeatherDTO getWeather(String cityName) {
        InputStream responseContent = establishConnection(cityName);
        Root response = extractResponse(responseContent);
        WeatherDTO responseDTO = RootToDto.map(response);
        LOGGER.info("weather petition request resolved: " + responseDTO);
        return responseDTO;
    }

    public InputStream establishConnection(String cityName) {
        String cityNameParsed = cityName.replace(" ", "%20");
        String urlRequest = String.format(uri + "?q=%s&appid=%s&units=%s",cityNameParsed, apiKey, units);
        LOGGER.info("Url requested: " + urlRequest);
        HttpUriRequest request = new HttpGet(urlRequest);
        HttpResponse response;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            LOGGER.warning("Error executing the request:  " + e);
            throw new ExecutingRequestException("Error executing the request: " + e);
        }
        int statusCode = response.getStatusLine().getStatusCode();
        switch (statusCode) {
            case 200:
                try {
                    return response.getEntity().getContent();
                } catch (IOException e) {
                    LOGGER.warning("IOException:  " + e);
                    throw new ResponseErrorException("Error retrieving the response code: " + e);

                }

            case 400:
                LOGGER.warning("Bad Request, Error code: " + statusCode);
                throw new BadRequestException("Bad Request, Error code: " + statusCode);
            case 404:
                LOGGER.warning("City Not Found: " + cityName);
                throw new NotFoundException("City Not Found: " + cityName + ", Status Code:" + statusCode);
            case 408:
                LOGGER.warning("Request Time Out, Error code: " + statusCode);
                throw new RequestTimeOutException("Request Time Out, Error code: " + statusCode);
            default:
                LOGGER.warning("Internal Error, Error code: " + statusCode);
                throw new InternalErrorException("Internal Error, Error code: " + statusCode);

        }
    }

    private Root extractResponse(InputStream responseContent) {
        String response = new BufferedReader(
                new InputStreamReader(responseContent, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining(","));
        try {
            return new ObjectMapper().readValue(response, Root.class);
        } catch (JsonProcessingException e) {
            LOGGER.warning("Error parsing JSON into Java Object: " + responseContent);
            throw new JsonErrorMapping("Error parsing JSON into Java Object: " + e);
        }
    }
}
