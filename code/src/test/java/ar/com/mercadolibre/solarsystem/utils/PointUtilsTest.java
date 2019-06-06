package ar.com.mercadolibre.solarsystem.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;
import java.awt.geom.Point2D;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PointUtilsTest {

    private static final String DEFAULT_MESSAGE = "Si grados = %d radio = %d entonces, la coordenada es %s";

    @Test
    public void whenRadiusIsZeroThenReturnZeroZeroPoint() {
        int radius = 0;
        Point2D pointZeroZero = new Point.Float(0, 0);
        Point2D result1 = PointUtils.pointTo(radius, 0);
        Point2D result2 = PointUtils.pointTo(radius, 100);
        assertEquals(getAssertMessage(0, radius, "(0,0)"), pointZeroZero, result1);
        assertEquals(getAssertMessage(100, radius, "(0,0)"), pointZeroZero, result2);
    }

    @Test
    public void whenDegreesIsZeroThenReturnYaxisIsZero() {
        int degrees = 0;
        Point2D result1 = PointUtils.pointTo(50, degrees);
        Point2D result2 = PointUtils.pointTo(100, degrees);
        assertEquals(getAssertMessage(degrees, 50, "(50,0)"), new Point(50, 0), result1);
        assertEquals(getAssertMessage(degrees, 100, "(100,0)"), new Point(100, 0), result2);
    }

    @Test
    public void whenDegreesIs90AndRadiusIsNotZeroThenReturnYaxisIsNotZero() {
        int radius = 150;
        int degrees = 90;
        Point2D result = PointUtils.pointTo(radius, degrees);
        assertEquals(getAssertMessage(degrees, radius, "(0,150)"), new Point(0, 150), result);
    }

    @Test
    public void whenDegreesAndRadiusIsNotZeroThenReturnYaxisAndXaxisIsNotZero() {
        int radius = 200;
        int degrees = 120;
        Point2D expected = new Point.Float(-100, 173.21f);
        Point2D result = PointUtils.pointTo(radius, degrees);
        assertEquals(getAssertMessage(degrees, radius, expected.toString()), expected, result);
    }

    @Test
    public void whenDegreesIsGreaterThat360ThenReturnIsOK() {
        int radius = 200;
        int degrees = 120 + 360;
        Point2D expected = PointUtils.pointTo(radius, 120);
        Point2D result = PointUtils.pointTo(radius, degrees);
        assertEquals(getAssertMessage(degrees, radius, expected.toString()), expected, result);
    }

    @Test
    public void whenDegreesIsLessThat0ThenReturnIsOK() {
        int radius = 200;
        int degrees = 120 - 360;
        Point2D expected = PointUtils.pointTo(radius, 120);
        Point2D result = PointUtils.pointTo(radius, degrees);
        assertEquals(getAssertMessage(degrees, radius, expected.toString()), expected, result);
    }

    @Test
    public void whenRadiousIsNegativeThenReturnIsOK() {
        int radius = -200;
        int degrees = 120 - 360;
        Point2D expected = PointUtils.pointTo(200, 120);
        Point2D result = PointUtils.pointTo(radius, degrees);
        assertEquals(getAssertMessage(degrees, radius, expected.toString()), expected, result);
    }

    private String getAssertMessage(int degrees, int radius, String expectedPoint) {
        return format(DEFAULT_MESSAGE, degrees, radius, expectedPoint);
    }
}
