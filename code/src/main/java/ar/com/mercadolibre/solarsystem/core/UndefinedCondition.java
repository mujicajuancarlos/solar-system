package ar.com.mercadolibre.solarsystem.core;

import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.WeatherType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * En caso de no cumplir con ninguna condicion llega a esta condicion para retornar @{@link WeatherType#UNDEFINED}
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
     * @return @{@link WeatherType#UNDEFINED}s
     */
    @Override
    public WeatherType getWeather(Galaxy galaxy, int numberOfDay) {
        return WeatherType.UNDEFINED;
    }
}
