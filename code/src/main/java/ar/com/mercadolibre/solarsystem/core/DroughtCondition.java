package ar.com.mercadolibre.solarsystem.core;

import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.WeatherType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static ar.com.mercadolibre.solarsystem.utils.PointUtils.allPointsInLine;

/**
 * Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el
 * sistema solar experimenta un período de sequía.
 */
@Component
@Qualifier("DroughtCondition")
public class DroughtCondition extends WeatherConditionChain {

    public DroughtCondition(@Qualifier("StableCondition") WeatherConditionChain nextCondition) {
        super(nextCondition);
    }

    /**
     * @param galaxy      instancia de la clase @{@link Galaxy}
     * @param numberOfDay numero positivo que representa el dia
     * @return @{@link WeatherType#DROUGHT} si el sol y los planetas se encuentran alineados
     * caso contrario ejecuta @{@link WeatherConditionChain#getWeather(Galaxy, int)}
     */
    @Override
    public WeatherType getWeather(Galaxy galaxy, int numberOfDay) {
        return allInLine(galaxy, numberOfDay) ? WeatherType.DROUGHT : super.getWeather(galaxy, numberOfDay);
    }

    private boolean allInLine(Galaxy galaxy, int numberOfDay) {
        List<Point> points = new ArrayList();
        points.add(galaxy.getSunPosition());
        points.addAll(galaxy.getCoordinatesForDay(numberOfDay));
        return allPointsInLine(points);
    }
}
