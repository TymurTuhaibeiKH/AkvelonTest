package com.tuhaibei.akvelonTest.controllers;

import com.tuhaibei.akvelonTest.models.CurrentWeather;
import com.tuhaibei.akvelonTest.services.LiveWeatherService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final LiveWeatherService liveWeatherService;

    public HomeController(LiveWeatherService liveWeatherService) {
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping("/api/city/{cityId}/weather/current")
    public CurrentWeather getCurrentWeather(@PathVariable(value = "cityId") long cityId, Model model) {
        return liveWeatherService.currentWeather(cityId);
    }
}