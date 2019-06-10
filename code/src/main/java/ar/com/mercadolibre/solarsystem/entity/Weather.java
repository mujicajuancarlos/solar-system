package ar.com.mercadolibre.solarsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WEATHER")
public class Weather {

    @Column(name = "DAY", nullable = false, unique = true)
    private int day;

    @Column(name = "WEATHER", nullable = false)
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

    @Override
    public String toString() {
        return "Weather{" +
                "day=" + day +
                ", weather='" + weather + '\'' +
                '}';
    }
}
