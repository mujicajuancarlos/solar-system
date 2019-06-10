package ar.com.mercadolibre.solarsystem.utils;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
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

    /**
     * Esta funcionalidad retorna true cuando el punto se encuentra dentro del poligono formado por los otros puntos y
     * falso en otro caso
     * Ninguno de los parametros puede ser @null
     *
     * @param point  es una instancia de @{@link Point}
     * @param points es una lista de @{@link List<Point>} con almenos 3 elementos, forman un poligono
     * @return true si el punto point se encuentra dentro del poligono formado por la lista de puntos
     */
    public static boolean polygonContainsPoint(Point point, List<Point> points) {
        notNull(point, "Point must not be null!");
        Polygon polygon = getPolygon(points);
        return polygon.contains(point);
    }

    /**
     * @param points es una lista de @{@link List<Point>} con almenos 2 elementos
     * @return el perimetro de los puntos
     */
    public static double perimeter(List<Point> points) {
        notNull(points, "Points must not be null!");
        isTrue(points.size() > 2, "Points must not be less or equals than two");
        long perimeter = 0;
        List<Point> copy = new ArrayList<>();
        copy.addAll(points);
        copy.add(points.get(0));
        for (int index = 0; index < points.size(); index++) {
            perimeter += distance(copy.get(index), copy.get(index + 1));
        }
        return perimeter;
    }

    /**
     * Esta funcionalidad verifica que todos los puntos de la lista points se encuentren alineados en la misma recta.
     * Retorna true si todos los puntos estan alineados y false cuando almenos un punto no esta alineado
     * Ninguno de los parametros puede ser @null
     *
     * @param points es una lista de @{@link List<Point>} con almenos 2 elementos
     * @return true si todos pertenecen a la misma recta
     */
    public static boolean allPointsInLine(List<Point> points) {
        notNull(points, "Points must not be null!");
        isTrue(points.size() >= 2, "Points must be less than one");
        if (points.size() == 2) {
            return true;
        }
        Line2D line = new Line2D.Float(points.get(0), points.get(1));
        return points.stream().skip(2)
                .allMatch(point -> line.ptLineDist(point) == 0.0);
    }

    private static Polygon getPolygon(List<Point> points) {
        notNull(points, "Points must not be null!");
        isTrue(points.size() > 2, "Points must not be less or equals than two");
        Polygon polygon = new Polygon();
        points.forEach(vertex -> polygon.addPoint(vertex.x, vertex.y));
        return polygon;
    }

    private static double distance(Point point1, Point point2) {
        double ac = Math.abs(point2.y - point1.y);
        double cb = Math.abs(point2.x - point1.x);
        return hypot(ac, cb);
    }
}
