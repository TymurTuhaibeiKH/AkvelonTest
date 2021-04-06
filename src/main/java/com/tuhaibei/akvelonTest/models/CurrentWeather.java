package com.tuhaibei.akvelonTest.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class CurrentWeather implements Serializable {
    @Id
    private Long id;
    private String name;
    private String country;
    private BigDecimal temperature;
    private Timestamp lastUpdate;

    public CurrentWeather() {
    }

    public CurrentWeather(Long id, String name, String country, BigDecimal temperature, Timestamp lastUpdate) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.temperature = temperature;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
