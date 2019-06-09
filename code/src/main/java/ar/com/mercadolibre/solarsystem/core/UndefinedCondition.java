package ar.com.mercadolibre.solarsystem.core;

import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.Weather;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * En caso de no cumplir con ninguna condicion llega a esta condicion para retornar @{@link Weather#UNDEFINED}
 */
@Component
@Qualifier("UndefinedCondition")
public class UndefinedCondition extends WeatherConditionChain {

    public UndefinedCondition() {
        super(null);
    }

    /**
     * @param galaxy      instancia de la clase @{@link Galaxy}
     * @param numberOfDay numero positivo que representa el dia
     * @return @{@link Weather#UNDEFINED}s
     */
    @Override
    public Weather getWeather(Galaxy galaxy, int numberOfDay) {
        return Weather.UNDEFINED;
    }
}
