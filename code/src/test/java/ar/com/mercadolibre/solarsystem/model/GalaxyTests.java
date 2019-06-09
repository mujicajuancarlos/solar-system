package ar.com.mercadolibre.solarsystem.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GalaxyTests {

    private Galaxy galaxy;
    private Planet asgardPlanet;
    private Planet titanPlanet;

    @Before
    public void init() {
        galaxy = new Galaxy();
        asgardPlanet = new Planet("Asgard", 5, 1000);
        titanPlanet = new Planet("Titan", 15, 1500);
    }

    @Test
    public void testGetSunPoint() {
        Point expected = new Point(0, 0);
        assertEquals(expected, galaxy.getSunPosition());
    }

    @Test
    public void testGetPlanetsIsEmpty() {
        assertTrue(galaxy.getPlanets().isEmpty());
    }

    @Test
    public void testAddPlanet() {
        galaxy.addPlanet(asgardPlanet);
        assertFalse(galaxy.getPlanets().isEmpty());
        assertEquals(1, galaxy.getPlanets().size());
        assertEquals(asgardPlanet, galaxy.getPlanets().get(0));
    }

    @Test
    public void testGetPoints() {
        galaxy.addPlanet(asgardPlanet);
        galaxy.addPlanet(titanPlanet);
        assertFalse(galaxy.getCoordinatesForDay(0).isEmpty());
        assertEquals(2, galaxy.getCoordinatesForDay(0).size());
        assertEquals(new Point(1000, 0), galaxy.getCoordinatesForDay(0).get(0));
        assertEquals(new Point(1500, 0), galaxy.getCoordinatesForDay(0).get(1));
    }
}
