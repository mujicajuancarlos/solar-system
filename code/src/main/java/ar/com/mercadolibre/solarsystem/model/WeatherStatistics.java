package ar.com.mercadolibre.solarsystem.model;

import java.util.Objects;

public class WeatherStatistics {

    private int droughtDays;
    private int rainDays;
    private int maximumRainDay;
    private double maximunPerimeter;
    private int stableDays;

    public WeatherStatistics() {
        this.droughtDays = 0;
        this.rainDays = 0;
        this.stableDays = 0;
        this.maximumRainDay = -1;
        this.maximunPerimeter = 0;
    }

    public void increment(WeatherType weatherType) {
        switch (weatherType) {
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

    public void updateMaximumRainDay(int day, double perimeter) {
        if (perimeter > maximunPerimeter) {
            this.maximumRainDay = day;
            this.maximunPerimeter = perimeter;
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

    public double getMaximunPerimeter() {
        return maximunPerimeter;
    }

    public int getStableDays() {
        return stableDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherStatistics that = (WeatherStatistics) o;
        return droughtDays == that.droughtDays &&
                rainDays == that.rainDays &&
                maximumRainDay == that.maximumRainDay &&
                maximunPerimeter == that.maximunPerimeter &&
                stableDays == that.stableDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(droughtDays, rainDays, maximumRainDay, stableDays);
    }

    @Override
    public String toString() {
        return "WeatherStatistics{" +
                "droughtDays=" + droughtDays +
                ", rainDays=" + rainDays +
                ", maximumRainDay=" + maximumRainDay +
                ", maximunPerimeter=" + maximunPerimeter +
                ", stableDays=" + stableDays +
                '}';
    }
}
