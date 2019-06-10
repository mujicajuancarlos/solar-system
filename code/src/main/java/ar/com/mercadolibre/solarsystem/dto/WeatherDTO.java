package ar.com.mercadolibre.solarsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDTO {

    @JsonProperty("dia")
    private int day;

    @JsonProperty("clima")
    private String weather;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
