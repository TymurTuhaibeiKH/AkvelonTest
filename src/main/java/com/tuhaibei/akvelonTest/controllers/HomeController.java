package com.tuhaibei.akvelonTest.controllers;

import com.tuhaibei.akvelonTest.models.CurrentWeather;
import com.tuhaibei.akvelonTest.services.LiveWeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    private final LiveWeatherService liveWeatherService;

    public HomeController(LiveWeatherService liveWeatherService) {
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping("/current-weather")
    public CurrentWeather getCurrentWeather(Model model) {
        return liveWeatherService.getCurrentWeather("Kharkiv","ua");
    }
}