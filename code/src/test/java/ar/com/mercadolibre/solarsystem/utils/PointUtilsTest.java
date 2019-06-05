package ar.com.mercadolibre.solarsystem.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PointUtilsTest {

    private int distance;
    private int grades;

    @Test
    public void whenDistanceIsZeroThenPointZeroZero() {
        distance = 0;
        grades = 0;
        Point result = PointUtils.pointTo(distance, grades);
        assertEquals("Si el radio es 0 entonces la coordenada es (0,0)", new Point(0, 0), result);
    }
}
