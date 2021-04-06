package com.tuhaibei.akvelonTest.job;

import com.tuhaibei.akvelonTest.models.CurrentWeather;
import com.tuhaibei.akvelonTest.repo.CurrentWeatherRepository;
import com.tuhaibei.akvelonTest.services.LiveWeatherService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SimpleJob implements Job{

    @Autowired
    private LiveWeatherService liveWeatherService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        liveWeatherService.strategy(706483);
    }
}
