package ar.com.mercadolibre.solarsystem.core;

import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.WeatherType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static ar.com.mercadolibre.solarsystem.utils.PointUtils.allPointsInLine;

/**
 * Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están
 * alineados entre sí pero no están alineados con el sol.
 */
@Component
@Qualifier("StableCondition")
public class StableCondition extends WeatherConditionChain {

    public StableCondition(@Qualifier("RainCondition") WeatherConditionChain nextCondition) {
        super(nextCondition);
    }

    /**
     * @param galaxy      instancia de la clase @{@link Galaxy}
     * @param numberOfDay numero positivo que representa el dia
     * @return @{@link WeatherType#STABLE} si los planetas se encuentran alineados no incluye al sol
     * caso contrario ejecuta @{@link WeatherConditionChain#getWeather(Galaxy, int)}
     */
    @Override
    public WeatherType getWeather(Galaxy galaxy, int numberOfDay) {
        return planetsInLine(galaxy, numberOfDay) ? WeatherType.STABLE : super.getWeather(galaxy, numberOfDay);
    }

    private boolean planetsInLine(Galaxy galaxy, int numberOfDay) {
        return allPointsInLine(galaxy.getCoordinatesForDay(numberOfDay));
    }
}
