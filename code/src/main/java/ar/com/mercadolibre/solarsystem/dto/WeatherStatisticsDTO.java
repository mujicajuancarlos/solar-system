package ar.com.mercadolibre.solarsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherStatisticsDTO {

    @JsonProperty("dias_sequia")
    private int droughtDays;
    @JsonProperty("dias_lluvia")
    private int rainDays;
    @JsonProperty("dia_lluvia_maxima")
    private int maximumRainDay;
    @JsonProperty("dias_optimo")
    private int stableDays;

    public int getDroughtDays() {
        return droughtDays;
    }

    public void setDroughtDays(int droughtDays) {
        this.droughtDays = droughtDays;
    }

    public int getRainDays() {
        return rainDays;
    }

    public void setRainDays(int rainDays) {
        this.rainDays = rainDays;
    }

    public int getMaximumRainDay() {
        return maximumRainDay;
    }

    public void setMaximumRainDay(int maximumRainDay) {
        this.maximumRainDay = maximumRainDay;
    }

    public int getStableDays() {
        return stableDays;
    }

    public void setStableDays(int stableDays) {
        this.stableDays = stableDays;
    }
}
