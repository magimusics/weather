package ru.geel.getweather.model;

import java.sql.Date;

/**
 * Created by ivangeel on 26.04.17.
 */
public class Stats {

     String username;

     String date;

     String request;

     String city;

    private String description;

    private String temp;

    private String humidity;

    private String pressure;

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getRequest() {
        return request;
    }

    public String getTemp() {
        return temp;
    }

    public String getUsername() {
        return username;
    }
}
