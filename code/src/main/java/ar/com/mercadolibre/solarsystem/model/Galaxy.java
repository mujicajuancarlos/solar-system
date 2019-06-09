package ar.com.mercadolibre.solarsystem.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Galaxy {

    private final Point sunPosition;
    private final List<Planet> planets;

    public Galaxy() {
        this.sunPosition = new Point(0, 0);
        this.planets = new ArrayList<>();
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }

    public List<Point> getCoordinatesForDay(int numberOfDay) {
        return planets.stream()
                .map(planet -> planet.getPositionTo(numberOfDay))
                .collect(toList());
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public Point getSunPosition() {
        return sunPosition;
    }
}
