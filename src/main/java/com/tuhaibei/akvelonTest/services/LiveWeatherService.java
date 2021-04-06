package com.tuhaibei.akvelonTest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuhaibei.akvelonTest.constant.Constant;
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
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    CurrentWeatherRepository currentWeatherRepository;

    public LiveWeatherService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public CurrentWeather currentWeather(long city) {
        URI url = new UriTemplate(Constant.WEATHER_URL).expand(city, Constant.API_KEY);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (currentWeatherRepository.existsById(city)) {
            CurrentWeather currentWeather = currentWeatherRepository.findById(city).orElseThrow();
            currentWeather.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            currentWeather.setTemperature(updateTemp(response));
            currentWeatherRepository.save(currentWeather);
        } else {
            convert(response);
        }
           return currentWeatherRepository.findById(city).orElseThrow();
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

    private BigDecimal updateTemp(ResponseEntity<String> response){
        try {
        JsonNode root = objectMapper.readTree(response.getBody());
        return BigDecimal.valueOf(root.path("main").path("temp").asDouble());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}
