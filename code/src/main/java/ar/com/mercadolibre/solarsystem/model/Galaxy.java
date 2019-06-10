package ar.com.mercadolibre.solarsystem.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class Galaxy {

    private final Point sunPosition;
    private List<Planet> planets;

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

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public Point getSunPosition() {
        return sunPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Galaxy galaxy = (Galaxy) o;
        return sunPosition.equals(galaxy.sunPosition) &&
                planets.equals(galaxy.planets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sunPosition, planets);
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "sunPosition=" + sunPosition +
                ", planets=" + planets +
                '}';
    }
}
