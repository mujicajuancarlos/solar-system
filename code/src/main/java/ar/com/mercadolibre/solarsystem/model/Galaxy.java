package ar.com.mercadolibre.solarsystem.model;

import java.awt.*;
import java.util.List;

import static java.util.Collections.emptyList;

public class Galaxy {

    private final Point sunPosition;
    private final List<Planet> planets;

    public Galaxy() {
        this.sunPosition = new Point(0, 0);
        this.planets = emptyList();
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public Point getSunPosition() {
        return sunPosition;
    }
}
