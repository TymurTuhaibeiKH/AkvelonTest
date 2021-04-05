package com.tuhaibei.akvelonTest.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class CurrentWeather implements Serializable{

    private Long id;
    private String name;
    private String country;
    private BigDecimal temperature;

    public CurrentWeather(Long id, String name, String country, BigDecimal temperature) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }
}
