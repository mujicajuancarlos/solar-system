package ar.com.mercadolibre.solarsystem.utils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

import static java.lang.Math.*;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

public class PointUtils {

    /**
     * Calcula las coordenadas cartecianas respecto al punto (0,0)
     *
     * @param radius  es un numero positivo y representa la distancia desde el punto (0,0)
     * @param degrees es un numero positivo o negativo que representa el angulo desde el eje de abscisas (eje x+)
     * @return @{@link Point} donde las coordenadas X, Y son numeros enteros
     */
    public static Point pointTo(int radius, int degrees) {
        double angle = toRadians(degrees);
        int distance = abs(radius);
        int pointX = (int) round(distance * cos(angle));
        int pointY = (int) round(distance * sin(angle));
        return new Point(pointX, pointY);
    }

    public static boolean polygonContainsPoint(Point point, List<Point> points) {
        notNull(point, "Point must not be null!");
        notNull(points, "Points must not be null!");
        isTrue(points.size() > 2, "Points must not be less or equals than two");
        Polygon polygon = new Polygon();
        points.forEach(vertex -> polygon.addPoint(vertex.x, vertex.y));
        return polygon.contains(point.getX(), point.getY());
    }

    public static boolean allPointsOnLine(List<Point2D> points) {
        notNull(points, "Points must not be null!");
        isTrue(points.size() >= 2, "Points must be less than one");
        if (points.size() == 2) {
            return true;
        }
        return false;
    }
}
