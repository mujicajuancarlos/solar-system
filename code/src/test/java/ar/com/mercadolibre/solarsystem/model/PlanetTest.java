package ar.com.mercadolibre.solarsystem.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;

import static ar.com.mercadolibre.solarsystem.model.Planet.INITIAL_ANGLE;
import static ar.com.mercadolibre.solarsystem.utils.PointUtils.pointTo;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PlanetTest {

    private static final int ASGARD_PLANET_VELOCITY = 15;
    private static final int ASGARD_PLANET_DISTANCE = 1500;
    private static final int TITAN_PLANET_VELOCITY = -45;
    private static final int TITAN_PLANET_DISTANCE = 5000;

    private Planet asgardPlanet;
    private Planet titanPlanet;

    @Before
    public void init() {
        asgardPlanet = new Planet("Asgard", ASGARD_PLANET_VELOCITY, ASGARD_PLANET_DISTANCE);
        titanPlanet = new Planet("Titan", TITAN_PLANET_VELOCITY, TITAN_PLANET_DISTANCE);
    }

    @Test
    public void whenGetPointToDayZeroThenReturnInitialPoint() {
        Point expected = pointTo(ASGARD_PLANET_DISTANCE, INITIAL_ANGLE);
        assertEquals(expected, asgardPlanet.getPositionTo(0));
    }

    @Test
    public void whenGetPointToDaySix() {
        int day = 6;
        Point expected = pointTo(ASGARD_PLANET_DISTANCE, day * ASGARD_PLANET_VELOCITY);
        assertEquals(expected, asgardPlanet.getPositionTo(day));
    }

    @Test
    public void whenGetPointWithNegativeVelocity() {
        int day = 1;
        Point expected = pointTo(TITAN_PLANET_DISTANCE, day * TITAN_PLANET_VELOCITY);
        assertEquals(expected, titanPlanet.getPositionTo(day));
    }

    @Test
    public void whenGetPointToNextCycles() {
        int thirtyDegrees = 2 * ASGARD_PLANET_VELOCITY;
        Point expected = pointTo(ASGARD_PLANET_DISTANCE, thirtyDegrees);
        assertEquals(expected, asgardPlanet.getPositionTo(2)); // Cycle 0
        assertEquals(expected, asgardPlanet.getPositionTo(2 + 24)); // Cycle 1
        assertEquals(expected, asgardPlanet.getPositionTo(2 + 24 * 2)); // Cycle 2
        assertEquals(expected, asgardPlanet.getPositionTo(2 + 24 * 3)); // Cycle 3
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetPointWithNegativeDayThenThrowException() {
        asgardPlanet.getPositionTo(-1);
    }
}
