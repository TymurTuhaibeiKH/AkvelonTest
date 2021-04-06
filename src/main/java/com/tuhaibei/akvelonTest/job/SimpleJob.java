package com.tuhaibei.akvelonTest.job;

import com.tuhaibei.akvelonTest.repo.CurrentWeatherRepository;
import com.tuhaibei.akvelonTest.services.LiveWeatherService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleJob implements Job{

    @Autowired
    private LiveWeatherService liveWeatherService;
    @Autowired
    private CurrentWeatherRepository currentWeatherRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        currentWeatherRepository.findAll().forEach(x -> liveWeatherService.currentWeather(x.getId()));
    }
}
