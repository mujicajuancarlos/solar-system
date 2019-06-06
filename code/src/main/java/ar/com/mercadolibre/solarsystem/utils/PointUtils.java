package ar.com.mercadolibre.solarsystem.utils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.List;

import static java.lang.Math.*;
import static java.math.RoundingMode.HALF_UP;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

public class PointUtils {

    private static final int SCALE = 2;

    /**
     * Calcula las coordenadas cartecianas respecto al punto (0,0)
     *
     * @param radius  es un numero positivo y representa la distancia desde el punto (0,0)
     * @param degrees es un numero positivo o negativo que representa el angulo desde el eje de abscisas (eje x+)
     * @return @{@link Point.Float} con una precision decimal de {@value #SCALE}
     */
    public static Point2D pointTo(int radius, int degrees) {
        double angle = toRadians(degrees);
        float pointX = round(abs(radius) * cos(angle));
        float pointY = round(abs(radius) * sin(angle));
        return new Point.Float(pointX, pointY);
    }

    public static boolean polygonContains(Point2D point, List<Point2D> points) {
        notNull(point, "point can not be null");
        isTrue(points != null && points.size() > 2, "points can not be null and size greater that 2");
        FloatPolygon polygon = new FloatPolygon();
        points.forEach(vertex -> polygon.addPoint(vertex.getX(), vertex.getY()));
        return polygon.contains(point.getX(), point.getY());
    }

    public static boolean ruleContains(List<Point2D> points) {
        isTrue(points != null && points.size() > 1, "points can not be null and size greater that 1");
        return false;
    }

    /**
     * @param value
     * @return float redondeado con {@value #SCALE} decimales
     */
    private static float round(double value) {
        return new BigDecimal(value).setScale(SCALE, HALF_UP).floatValue();
    }
}
