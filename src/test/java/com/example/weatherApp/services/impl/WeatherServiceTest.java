package com.example.weatherApp.services.impl;

import com.example.weatherApp.dto.WeatherDTO;
import com.example.weatherApp.exceptions.*;
import com.example.weatherApp.service.WeatherService;
import com.example.weatherApp.service.impl.WeatherServiceImpl;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.any;


@RunWith(SpringRunner.class)
@TestPropertySource(locations = "/test.properties")
public class WeatherServiceTest {

    private WeatherService weatherServiceTest;
    private static final double DELTA = 1e-5;

    @Mock
    private HttpClient mockedClient;

    @Value("${weathermap.uri}")
    private String uri;
    @Value("${weathermap.apikey}")
    private String apiKey;
    @Value("${weathermap.units}")
    private String units;

@Value("${testFilePath.London}")
private String londonFile;

    @Value("${testFilePath.Mosedis}")
    private String MosedisFile;

    @Before
    public void setUp() {
        weatherServiceTest = new WeatherServiceImpl(mockedClient);
    }

    @Test
    public void geWeatherOk1Test() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(londonFile);

        HttpResponse mockedResponse = Mockito.mock(HttpResponse.class);
        HttpEntity mockedEntity = Mockito.mock(HttpEntity.class);
        StatusLine mockedStatusLine = Mockito.mock(StatusLine.class);

        Mockito.doReturn(mockedResponse).when(mockedClient).execute(any());
        Mockito.doReturn(mockedStatusLine).when(mockedResponse).getStatusLine();
        Mockito.doReturn(200).when(mockedStatusLine).getStatusCode();
        Mockito.doReturn(mockedEntity).when(mockedResponse).getEntity();
        Mockito.doReturn(inputStream).when(mockedEntity).getContent();

        WeatherDTO response = weatherServiceTest.getWeather("London");

        Assert.assertEquals("Condition does not match", "overcast clouds", response.getCondition());
        Assert.assertEquals(11.16, response.getTemperature(), DELTA);
        Assert.assertEquals(27.80, response.getWindSpeed(), DELTA);
    }

    @Test
    public void geWeatherOk2Test() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(MosedisFile);

        HttpResponse mockedResponse = Mockito.mock(HttpResponse.class);
        HttpEntity mockedEntity = Mockito.mock(HttpEntity.class);
        StatusLine mockedStatusLine = Mockito.mock(StatusLine.class);

        Mockito.doReturn(mockedResponse).when(mockedClient).execute(any());
        Mockito.doReturn(mockedStatusLine).when(mockedResponse).getStatusLine();
        Mockito.doReturn(200).when(mockedStatusLine).getStatusCode();
        Mockito.doReturn(mockedEntity).when(mockedResponse).getEntity();
        Mockito.doReturn(inputStream).when(mockedEntity).getContent();

        WeatherDTO response = weatherServiceTest.getWeather("Mosedis");

        Assert.assertEquals("Condition does not match", "very heavy rain", response.getCondition());
        Assert.assertEquals(2.49, response.getTemperature(), DELTA);
        Assert.assertEquals(9.26, response.getWindSpeed(), DELTA);
    }

    @Test(expected = JsonErrorMapping.class)
    public void ResponseNotMapInRootTest() throws Exception {
        String londonFakeFile = "LondonFake.txt";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(londonFakeFile);

        HttpResponse mockedResponse = Mockito.mock(HttpResponse.class);
        HttpEntity mockedEntity = Mockito.mock(HttpEntity.class);
        StatusLine mockedStatusLine = Mockito.mock(StatusLine.class);

        Mockito.doReturn(mockedResponse).when(mockedClient).execute(any());
        Mockito.doReturn(mockedStatusLine).when(mockedResponse).getStatusLine();
        Mockito.doReturn(200).when(mockedStatusLine).getStatusCode();
        Mockito.doReturn(mockedEntity).when(mockedResponse).getEntity();
        Mockito.doReturn(inputStream).when(mockedEntity).getContent();

        weatherServiceTest.getWeather("London");

    }

    @Test(expected = ExecutingRequestException.class)
    public void errorExecutingRequestTest() throws Exception {
        Mockito.doThrow(IOException.class).when(mockedClient).execute(any());

        weatherServiceTest.getWeather("London");
    }

    @Test(expected = ResponseErrorException.class)
    public void errorGettingContentFromResponseTest() throws Exception {
        HttpResponse mockedResponse = Mockito.mock(HttpResponse.class);
        HttpEntity mockedEntity = Mockito.mock(HttpEntity.class);
        StatusLine mockedStatusLine = Mockito.mock(StatusLine.class);

        Mockito.doReturn(mockedResponse).when(mockedClient).execute(any());
        Mockito.doReturn(mockedStatusLine).when(mockedResponse).getStatusLine();
        Mockito.doReturn(200).when(mockedStatusLine).getStatusCode();
        Mockito.doReturn(mockedEntity).when(mockedResponse).getEntity();
        Mockito.doThrow(IOException.class).when(mockedEntity).getContent();

        weatherServiceTest.getWeather("London");
    }

    @Test(expected = InternalErrorException.class)
    public void codeStatusReceivedNotValidTest() throws Exception {
        HttpResponse mockedResponse = Mockito.mock(HttpResponse.class);
        StatusLine mockedStatusLine = Mockito.mock(StatusLine.class);

        Mockito.doReturn(mockedResponse).when(mockedClient).execute(any());
        Mockito.doReturn(mockedStatusLine).when(mockedResponse).getStatusLine();
        Mockito.doReturn(503).when(mockedStatusLine).getStatusCode();

        weatherServiceTest.getWeather("London");
    }


    @Test(expected = BadRequestException.class)
    public void geWeatherBadRequestTest() throws IOException {

        HttpResponse resp = Mockito.mock(HttpResponse.class);
        StatusLine mockedStatusLine = Mockito.mock(StatusLine.class);
        Mockito.doReturn(resp).when(mockedClient).execute(any());
        Mockito.doReturn(mockedStatusLine).when(resp).getStatusLine();
        Mockito.doReturn(400).when(mockedStatusLine).getStatusCode();
        weatherServiceTest.getWeather("London");
    }

    @Test(expected = NotFoundException.class)
    public void geWeatherNotFoundTest() throws IOException {
        HttpResponse resp = Mockito.mock(HttpResponse.class);
        StatusLine mockedStatusLine = Mockito.mock(StatusLine.class);
        Mockito.doReturn(resp).when(mockedClient).execute(any());
        Mockito.doReturn(mockedStatusLine).when(resp).getStatusLine();
        Mockito.doReturn(404).when(mockedStatusLine).getStatusCode();
        weatherServiceTest.getWeather("London");
    }

    @Test(expected = RequestTimeOutException.class)
    public void geWeatherNotFound2Test() throws IOException {
        HttpResponse resp = Mockito.mock(HttpResponse.class);
        StatusLine mockedStatusLine = Mockito.mock(StatusLine.class);
        Mockito.doReturn(resp).when(mockedClient).execute(any());
        Mockito.doReturn(mockedStatusLine).when(resp).getStatusLine();
        Mockito.doReturn(408).when(mockedStatusLine).getStatusCode();
        weatherServiceTest.getWeather("London");
    }
}
