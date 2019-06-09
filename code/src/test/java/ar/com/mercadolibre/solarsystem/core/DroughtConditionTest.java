package ar.com.mercadolibre.solarsystem.core;

import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.Weather;
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
public class DroughtConditionTest {

    @Mock
    private Galaxy galaxy;
    private DroughtCondition weatherCondition;

    @Before
    public void init() {
        galaxy = mock(Galaxy.class);
        weatherCondition = new DroughtCondition(null);
        when(galaxy.getSunPosition()).thenReturn(new Point(0, 0));
    }

    @Test
    public void testGetDroughtWeather() {
        List<Point> inLine = Arrays.asList(new Point(0, 500), new Point(0, 1000), new Point(0, 1500));
        when(galaxy.getCoordinatesForDay(eq(0))).thenReturn(inLine);
        assertEquals(Weather.DROUGHT, weatherCondition.getWeather(galaxy, 0));
    }

    @Test
    public void testGetWeatherReturnUndefined() {
        List<Point> misaligned = Arrays.asList(new Point(100, 500), new Point(0, 1000), new Point(0, 1500));
        when(galaxy.getCoordinatesForDay(eq(0))).thenReturn(misaligned);
        assertEquals(Weather.UNDEFINED, weatherCondition.getWeather(galaxy, 0));
    }

}
