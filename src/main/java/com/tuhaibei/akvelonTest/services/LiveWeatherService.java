package com.tuhaibei.akvelonTest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuhaibei.akvelonTest.models.CurrentWeather;
import com.tuhaibei.akvelonTest.repo.CurrentWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.sql.Timestamp;

@Service
public class LiveWeatherService {

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?id={city id}&appid={API key}";

    private static final String apiKey = "f86bb32f0e48540092c369d6ff05d21e";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    CurrentWeatherRepository currentWeatherRepository;

    public LiveWeatherService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public CurrentWeather strategy(long city) {
        if (currentWeatherRepository.existsById(city)) {
            CurrentWeather currentWeather = currentWeatherRepository.findById(city).orElseThrow();
            currentWeather.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            currentWeatherRepository.save(currentWeather);
        } else {
            currentWeather(city);
        }
        return currentWeatherRepository.findById(city).orElseThrow();
    }

    private void currentWeather(long city) {
        URI url = new UriTemplate(WEATHER_URL).expand(city, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        convert(response);
    }

    private void convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            currentWeatherRepository.save(new CurrentWeather(
                    Long.parseLong(String.valueOf(root.path("id").asLong())),
                    root.path("name").asText(),
                    root.path("sys").path("country").asText(),
                    BigDecimal.valueOf(root.path("main").path("temp").asDouble()),
                    new Timestamp(System.currentTimeMillis())));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}
