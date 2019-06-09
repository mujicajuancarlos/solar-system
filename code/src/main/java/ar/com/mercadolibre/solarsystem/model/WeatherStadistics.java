package ar.com.mercadolibre.solarsystem.model;

import java.util.Objects;

public class WeatherStadistics {

    private int droughtDays;
    private int rainDays;
    private int maximumRainDay;
    private int stableDays;

    public WeatherStadistics() {
        this.droughtDays = 0;
        this.rainDays = 0;
        this.stableDays = 0;
        this.maximumRainDay = -1;
    }

    public void increment(Weather weather) {
        switch (weather) {
            case RAIN:
                rainDays++;
                break;
            case DROUGHT:
                droughtDays++;
                break;
            case STABLE:
                stableDays++;
                break;
        }
    }

    public void updateMaximumRainDay(int newValue) {
        if (newValue > maximumRainDay) {
            this.maximumRainDay = newValue;
        }
    }

    public int getDroughtDays() {
        return droughtDays;
    }

    public int getRainDays() {
        return rainDays;
    }

    public int getMaximumRainDay() {
        return maximumRainDay;
    }

    public int getStableDays() {
        return stableDays;
    }

    @Override
    public String toString() {
        return "WeatherStadistics{" +
                "droughtDays=" + droughtDays +
                ", rainDays=" + rainDays +
                ", maximumRainDay=" + maximumRainDay +
                ", stableDays=" + stableDays +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherStadistics that = (WeatherStadistics) o;
        return droughtDays == that.droughtDays &&
                rainDays == that.rainDays &&
                maximumRainDay == that.maximumRainDay &&
                stableDays == that.stableDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(droughtDays, rainDays, maximumRainDay, stableDays);
    }
}
