package ar.com.mercadolibre.solarsystem.model;

import ar.com.mercadolibre.solarsystem.utils.PointUtils;

import java.awt.*;

import static org.springframework.util.Assert.isTrue;

public class Planet {

    public static final int INITIAL_ANGLE = 0;

    private String name;
    private int velocity;
    private int distance;

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
}
