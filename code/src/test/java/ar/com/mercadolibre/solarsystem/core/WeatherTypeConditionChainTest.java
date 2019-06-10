package ar.com.mercadolibre.solarsystem.core;

import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.WeatherType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DroughtCondition.class, RainCondition.class, StableCondition.class, UndefinedCondition.class})
public class WeatherTypeConditionChainTest {

    @Mock
    private Galaxy galaxy;
    @Autowired
    @Qualifier("DroughtCondition")
    private WeatherConditionChain weatherCondition;

    @Before
    public void init() {
        galaxy = mock(Galaxy.class);
        when(galaxy.getSunPosition()).thenReturn(new Point(0, 0));
    }

    @Test
    public void testGetDroughtWeather() {
        List<Point> inLine = Arrays.asList(new Point(0, 500), new Point(0, 1000), new Point(0, 1500));
        when(galaxy.getCoordinatesForDay(eq(0))).thenReturn(inLine);
        assertEquals(WeatherType.DROUGHT, weatherCondition.getWeather(galaxy, 0));
    }

    @Test
    public void testGetStableWeather() {
        List<Point> inLine = Arrays.asList(new Point(100, 100), new Point(200, 0), new Point(0, 200));
        when(galaxy.getCoordinatesForDay(eq(1))).thenReturn(inLine);
        assertEquals(WeatherType.STABLE, weatherCondition.getWeather(galaxy, 1));
    }

    @Test
    public void testGetRainWeather() {
        List<Point> polygon = Arrays.asList(new Point(-500, -1000), new Point(250, -1000), new Point(-100, 2000));
        when(galaxy.getCoordinatesForDay(eq(1))).thenReturn(polygon);
        assertEquals(WeatherType.RAIN, weatherCondition.getWeather(galaxy, 1));
    }

    @Test
    public void testGetWeatherReturnUndefined() {
        List<Point> polygon = Arrays.asList(new Point(-500, 200), new Point(100, 50), new Point(-200, 1000));
        when(galaxy.getCoordinatesForDay(eq(1))).thenReturn(polygon);
        assertEquals(WeatherType.UNDEFINED, weatherCondition.getWeather(galaxy, 1));
    }


}
