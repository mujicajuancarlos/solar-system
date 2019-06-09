package ar.com.mercadolibre.solarsystem.core;

import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.Weather;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static ar.com.mercadolibre.solarsystem.utils.PointUtils.polygonContainsPoint;

/**
 * Cuando los tres planetas no están alineados, forman entre sí un triángulo. Es sabido que en el
 * momento en el que el sol se encuentra dentro del triángulo, el sistema solar experimenta un
 * período de lluvia, teniendo éste, un pico de intensidad cuando el perímetro del triángulo está en
 * su máximo.
 */
@Component
@Qualifier("RainCondition")
public class RainCondition extends WeatherConditionChain {

    public RainCondition(@Qualifier("UndefinedCondition") WeatherConditionChain nextCondition) {
        super(nextCondition);
    }

    /**
     * @param galaxy      instancia de la clase @{@link Galaxy}
     * @param numberOfDay numero positivo que representa el dia
     * @return @{@link Weather#RAIN} si los planetas forman un poligono (triangulo) y el sol esta contenido en el mismo
     * caso contrario ejecuta @{@link WeatherConditionChain#getWeather(Galaxy, int)}
     */
    @Override
    public Weather getWeather(Galaxy galaxy, int numberOfDay) {
        return sunIntoPlanets(galaxy, numberOfDay) ? Weather.RAIN : super.getWeather(galaxy, numberOfDay);
    }

    private boolean sunIntoPlanets(Galaxy galaxy, int numberOfDay) {
        return polygonContainsPoint(galaxy.getSunPosition(), galaxy.getCoordinatesForDay(numberOfDay));
    }
}