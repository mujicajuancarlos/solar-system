package ar.com.mercadolibre.solarsystem.service;

import ar.com.mercadolibre.solarsystem.core.WeatherConditionChain;
import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.WeatherStatistics;
import ar.com.mercadolibre.solarsystem.model.WeatherType;
import ar.com.mercadolibre.solarsystem.service.impl.DefaultMeteorologicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static ar.com.mercadolibre.solarsystem.model.WeatherType.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class MeteorologicServiceTest {

    @InjectMocks
    private DefaultMeteorologicService meteorologicService;
    @Mock
    private WeatherConditionChain weatherCondition;
    @Mock
    private Galaxy galaxy;
    @Mock
    private Date startDate;
    private WeatherStatistics statistics;

    @Before
    public void init() {
        statistics = new WeatherStatistics();
        startDate = new Date();
        initMocks(this);
        when(weatherCondition.getWeather(eq(galaxy), anyInt())).thenReturn(WeatherType.UNDEFINED);
    }

    @Test
    public void testAllUndefinedWeather() {
        assertEquals(statistics, meteorologicService.calculateWeatherStatistics(1));
    }

    @Test
    public void testProcessAllDays() {
        when(weatherCondition.getWeather(eq(galaxy), eq(5))).thenReturn(DROUGHT);
        when(weatherCondition.getWeather(eq(galaxy), eq(9))).thenReturn(DROUGHT);
        when(weatherCondition.getWeather(eq(galaxy), eq(15))).thenReturn(WeatherType.RAIN);
        when(weatherCondition.getWeather(eq(galaxy), eq(50))).thenReturn(WeatherType.STABLE);
        List<Point> points = Arrays.asList(new Point(1, 0), new Point(2, 0), new Point(1, 1));
        when(galaxy.getCoordinatesForDay(eq(15))).thenReturn(points);
        increment(DROUGHT).increment(DROUGHT).increment(STABLE).increment(RAIN);
        statistics.updateMaximumRainDay(15, 3.0);
        assertEquals(statistics, meteorologicService.calculateWeatherStatistics(1));
    }

    @Test
    public void testProcessAllTwoYearss() {
        when(weatherCondition.getWeather(eq(galaxy), eq(367))).thenReturn(WeatherType.STABLE);
        increment(STABLE);
        assertEquals(statistics, meteorologicService.calculateWeatherStatistics(2));
    }

    private MeteorologicServiceTest increment(WeatherType weatherType) {
        statistics.increment(weatherType);
        return this;
    }
}
