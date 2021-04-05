package com.tuhaibei.akvelonTest.repo;

import com.tuhaibei.akvelonTest.models.CurrentWeather;
import org.springframework.data.repository.CrudRepository;

public interface CurrentWeatherRepository extends CrudRepository<CurrentWeather, Long> {
}
