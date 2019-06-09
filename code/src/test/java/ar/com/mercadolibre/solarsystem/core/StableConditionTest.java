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
public class StableConditionTest {

    @Mock
    private Galaxy galaxy;
    private StableCondition stableCondition;

    @Before
    public void init() {
        galaxy = mock(Galaxy.class);
        stableCondition = new StableCondition(null);
        when(galaxy.getSunPosition()).thenReturn(new Point(0, 0));
    }

    @Test
    public void testGetStableWeather() {
        List<Point> inLine = Arrays.asList(new Point(100, 100), new Point(200, 0), new Point(0, 200));
        when(galaxy.getCoordinatesForDay(eq(1))).thenReturn(inLine);
        assertEquals(Weather.STABLE, stableCondition.getWeather(galaxy, 1));
    }

    @Test
    public void testGetWeatherReturnUndefined() {
        List<Point> misaligned = Arrays.asList(new Point(100, 100), new Point(0, 50), new Point(1000, 1500));
        when(galaxy.getCoordinatesForDay(eq(1))).thenReturn(misaligned);
        assertEquals(Weather.UNDEFINED, stableCondition.getWeather(galaxy, 1));
    }

}
