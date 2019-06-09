package ar.com.mercadolibre.solarsystem.model;

import ar.com.mercadolibre.solarsystem.utils.PointUtils;

import java.awt.*;
import java.util.Objects;

import static org.springframework.util.Assert.isTrue;

public class Planet {

    public static final int INITIAL_ANGLE = 0;

    private final String name;
    private final int velocity;
    private final int distance;

    public Planet(String name, int velocity, int distance) {
        this.name = name;
        this.velocity = velocity;
        this.distance = distance;
    }

    /**
     * Esta funcionalidad calcula las coordenadas correspondientes al dia ingresado
     * para realizar este calculo se tiene en cuenta la distancia y velocidad del planeta
     *
     * @param day es un numero entero mayor o igual a cero
     * @return una instancia de @{@link Point} con las coordenadas x,y del día solicitado
     */
    public Point getPositionTo(int day) {
        isTrue(day >= 0, "Day must not be less or equals than zero");
        int angle = (day * velocity) % 360;
        return PointUtils.pointTo(distance, angle + INITIAL_ANGLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(name, planet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
