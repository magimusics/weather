package ru.geel.getweather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ivangeel on 24.04.17.
 */


public class Weather implements Serializable{

    private String name;

    private Map<String, Object> weather;

    private Map<String, Object> main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weather) {
        this.weather = weather.get(0);

    }

    @JsonProperty("main")
    public void setMain(Map<String, Object> main) {
        this.main = main;

    }

    public Map<String, Object> getWeather() {
        return weather;
    }

    public Map<String, Object> getMain() {
        return main;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "name='" + name + '\'' +
                '}';
    }
}
