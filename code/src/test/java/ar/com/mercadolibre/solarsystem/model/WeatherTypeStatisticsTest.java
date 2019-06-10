package ar.com.mercadolibre.solarsystem.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class WeatherTypeStatisticsTest {

    private WeatherStatistics stadistic;

    @Before
    public void init() {
        stadistic = new WeatherStatistics();
    }

    @Test
    public void testInitializeValues() {
        assertEquals(0, stadistic.getDroughtDays());
        assertEquals(0, stadistic.getRainDays());
        assertEquals(0, stadistic.getStableDays());
        assertEquals(-1, stadistic.getMaximumRainDay());
    }

    @Test
    public void testIncrementDroughtWeather() {
        stadistic.increment(WeatherType.DROUGHT);
        assertEquals(1, stadistic.getDroughtDays());
        assertEquals(0, stadistic.getRainDays());
        assertEquals(0, stadistic.getStableDays());
        assertEquals(-1, stadistic.getMaximumRainDay());
    }

    @Test
    public void testIncrementRainWeather() {
        stadistic.increment(WeatherType.RAIN);
        assertEquals(0, stadistic.getDroughtDays());
        assertEquals(1, stadistic.getRainDays());
        assertEquals(0, stadistic.getStableDays());
        assertEquals(-1, stadistic.getMaximumRainDay());
    }

    @Test
    public void testIncrementStableWeather() {
        stadistic.increment(WeatherType.STABLE);
        assertEquals(0, stadistic.getDroughtDays());
        assertEquals(0, stadistic.getRainDays());
        assertEquals(1, stadistic.getStableDays());
        assertEquals(-1, stadistic.getMaximumRainDay());
    }

    @Test
    public void testIncrementUndefinedWeather() {
        stadistic.increment(WeatherType.UNDEFINED);
        assertEquals(0, stadistic.getDroughtDays());
        assertEquals(0, stadistic.getRainDays());
        assertEquals(0, stadistic.getStableDays());
        assertEquals(-1, stadistic.getMaximumRainDay());
    }

    @Test
    public void testUpdateMaximumRainDay() {
        stadistic.updateMaximumRainDay(58, 200);
        assertEquals(0, stadistic.getDroughtDays());
        assertEquals(0, stadistic.getRainDays());
        assertEquals(0, stadistic.getStableDays());
        assertEquals(58, stadistic.getMaximumRainDay());
    }
}
