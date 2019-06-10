package ar.com.mercadolibre.solarsystem.core;

import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.WeatherType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class RainConditionTest {

    @Mock
    private Galaxy galaxy;
    private RainCondition rainCondition;

    @Before
    public void init() {
        galaxy = mock(Galaxy.class);
        rainCondition = new RainCondition(null);
        when(galaxy.getSunPosition()).thenReturn(new Point(0, 0));
    }

    @Test
    public void testGetRainWeather() {
        List<Point> polygon = Arrays.asList(new Point(-500, -1000), new Point(250, -1000), new Point(-100, 2000));
        when(galaxy.getCoordinatesForDay(eq(1))).thenReturn(polygon);
        assertEquals(WeatherType.RAIN, rainCondition.getWeather(galaxy, 1));
    }

    @Test
    public void testGetWeatherReturnUndefined() {
        List<Point> polygon = Arrays.asList(new Point(-500, 200), new Point(100, 50), new Point(-200, 1000));
        when(galaxy.getCoordinatesForDay(eq(1))).thenReturn(polygon);
        assertEquals(WeatherType.UNDEFINED, rainCondition.getWeather(galaxy, 1));
    }

}
