package ar.com.mercadolibre.solarsystem.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PointUtilsTest {

    private static final String DEFAULT_MESSAGE = "Si grados = %d radio = %d entonces, la coordenada es %s";

    @Test
    public void whenRadiusIsZeroThenReturnZeroZeroPoint() {
        int radius = 0;
        Point pointZeroZero = new Point(0, 0);
        Point result1 = PointUtils.pointTo(radius, 0);
        Point result2 = PointUtils.pointTo(radius, 100);
        assertEquals(getAssertMessage(0, radius, "(0,0)"), pointZeroZero, result1);
        assertEquals(getAssertMessage(100, radius, "(0,0)"), pointZeroZero, result2);
    }

    @Test
    public void whenDegreesIsZeroThenReturnYaxisIsZero() {
        int degrees = 0;
        Point result1 = PointUtils.pointTo(50, degrees);
        Point result2 = PointUtils.pointTo(100, degrees);
        assertEquals(getAssertMessage(degrees, 50, "(50,0)"), new Point(50, 0), result1);
        assertEquals(getAssertMessage(degrees, 100, "(100,0)"), new Point(100, 0), result2);
    }

    @Test
    public void whenDegreesIs90AndRadiusIsNotZeroThenReturnYaxisIsNotZero() {
        int radius = 150;
        int degrees = 90;
        Point result = PointUtils.pointTo(radius, degrees);
        assertEquals(getAssertMessage(degrees, radius, "(0,150)"), new Point(0, 150), result);
    }

    @Test
    public void whenDegreesAndRadiusIsNotZeroThenReturnYaxisAndXaxisIsNotZero() {
        int radius = 200;
        int degrees = 120;
        Point expected = new Point(-100, 173);
        Point result = PointUtils.pointTo(radius, degrees);
        assertEquals(getAssertMessage(degrees, radius, expected.toString()), expected, result);
    }

    @Test
    public void whenDegreesIsGreaterThat360ThenReturnIsOK() {
        int radius = 200;
        int degrees = 120 + 360;
        Point expected = PointUtils.pointTo(radius, 120);
        Point result = PointUtils.pointTo(radius, degrees);
        assertEquals(getAssertMessage(degrees, radius, expected.toString()), expected, result);
    }

    @Test
    public void whenDegreesIsLessThat0ThenReturnIsOK() {
        int radius = 200;
        int degrees = 120 - 360;
        Point expected = PointUtils.pointTo(radius, 120);
        Point result = PointUtils.pointTo(radius, degrees);
        assertEquals(getAssertMessage(degrees, radius, expected.toString()), expected, result);
    }

    @Test
    public void whenRadiousIsNegativeThenReturnIsOK() {
        int radius = -200;
        int degrees = 120 - 360;
        Point expected = PointUtils.pointTo(200, 120);
        Point result = PointUtils.pointTo(radius, degrees);
        assertEquals(getAssertMessage(degrees, radius, expected.toString()), expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPolygonContainsNullPointIsNullThenThrowException() {
        PointUtils.polygonContainsPoint(null, emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNullPolygonContainsPointThenThrowException() {
        PointUtils.polygonContainsPoint(new Point(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMissingPointsThenThrowException() {
        Point point = new Point();
        PointUtils.polygonContainsPoint(point, asList(point, point));
    }

    @Test
    public void whenPointInsideThenReturnTrue() {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(0, 8);
        Point point3 = new Point(2, -1);
        List<Point> polygonPoints = asList(new Point(-3, -3), new Point(6, 0), new Point(-1, 10));
        assertTrue(PointUtils.polygonContainsPoint(point1, polygonPoints));
        assertTrue(PointUtils.polygonContainsPoint(point2, polygonPoints));
        assertTrue(PointUtils.polygonContainsPoint(point3, polygonPoints));
    }

    @Test
    public void whenPointNotInsideThenReturnFalse() {
        Point point1 = new Point(-1, 0);
        Point point2 = new Point(1, -1);
        Point point3 = new Point(0, 2);
        List<Point> polygonPoints = asList(new Point(-1, -1), new Point(1, 0), new Point(0, 1));
        assertFalse(PointUtils.polygonContainsPoint(point1, polygonPoints));
        assertFalse(PointUtils.polygonContainsPoint(point2, polygonPoints));
        assertFalse(PointUtils.polygonContainsPoint(point3, polygonPoints));
    }

    @Test
    public void whenLimitPointThenReturnTrue() {
        Point point1 = new Point(-1, 0);
        Point point2 = new Point(-1, -1);
        Point point3 = new Point(0, -1);
        List<Point> polygonPoints = asList(new Point(-1, -1), new Point(-1, 1), new Point(1, 1), new Point(1, -1));
        assertTrue(PointUtils.polygonContainsPoint(point1, polygonPoints));
        assertTrue(PointUtils.polygonContainsPoint(point2, polygonPoints));
        assertTrue(PointUtils.polygonContainsPoint(point3, polygonPoints));
    }

    @Test
    public void whenAllPointsInsideThenReturnTrue() {
        List<Point> points1 = asList(new Point(2, 1), new Point(0, 1), new Point(1, 1));
        List<Point> points2 = asList(new Point(-1, -1), new Point(0, 0), new Point(1, 1));
        List<Point> points3 = asList(new Point(-5, 1), new Point(-5, -5), new Point(-5, -2));
        assertTrue(PointUtils.allPointsInLine(points1));
        assertTrue(PointUtils.allPointsInLine(points2));
        assertTrue(PointUtils.allPointsInLine(points3));
    }

    @Test
    public void whenTwoPointsThenReturnTrue() {
        List<Point> points1 = asList(new Point(2, 1), new Point(0, 500));
        assertTrue(PointUtils.allPointsInLine(points1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSizePointsIsOneThenThrowException() {
        List<Point> points1 = asList(new Point(2, 1));
        assertTrue(PointUtils.allPointsInLine(points1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPointsIsEmptyThenThrowException() {
        assertTrue(PointUtils.allPointsInLine(emptyList()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPointsIsNullThenThrowException() {
        assertTrue(PointUtils.allPointsInLine(null));
    }

    @Test
    public void whenAnyPointNotInsideThenReturnFalse() {
        List<Point> points1 = asList(new Point(2, 1), new Point(0, 1), new Point(1, 2));
        List<Point> points2 = asList(new Point(-1, -1), new Point(0, 0), new Point(1, 2));
        List<Point> points3 = asList(new Point(-4, 2), new Point(-5, -5), new Point(-5, -2));
        assertFalse(PointUtils.allPointsInLine(points1));
        assertFalse(PointUtils.allPointsInLine(points2));
        assertFalse(PointUtils.allPointsInLine(points3));
    }

    @Test
    public void testPerimeter() {
        Point point1 = new Point(-2, 0);
        Point point2 = new Point(-2, 1);
        Point point3 = new Point(1, 1);
        Point point4 = new Point(1, 0);
        List<Point> points = asList(point1, point2, point3, point4);
        assertEquals(8.0, PointUtils.perimeter(points), 0.0);
    }

    private String getAssertMessage(int degrees, int radius, String expectedPoint) {
        return format(DEFAULT_MESSAGE, degrees, radius, expectedPoint);
    }
}
