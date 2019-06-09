package ar.com.mercadolibre.solarsystem.core;

import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.Weather;

import static java.util.Optional.ofNullable;

/**
 * WeatherConditionChain implementa el patron chain of responsability
 * Registra en la cadena las posibles condiciones climaticas ordenadas por importancia,
 * si las condiciones se cumplen retorna el clima especifico si no se cumple pasa a la siguiente condición
 * hasta llegar al ultimo de la cadena en ese caso retirno #UNDEFINED
 */
public abstract class WeatherConditionChain {

    protected final WeatherConditionChain nextCondition;

    public WeatherConditionChain(WeatherConditionChain nextCondition) {
        this.nextCondition = nextCondition;
    }

    /**
     * Esta funcionalidad retorna el clima @{@link Weather}
     * correspondiente a la galaxia y dia especificados en los parametros
     *
     * @param galaxy      instancia de la clase @{@link Galaxy}
     * @param numberOfDay numero positivo que representa el dia
     * @return @{@link Weather} correspondiente al dia y galaxia
     */
    public Weather getWeather(Galaxy galaxy, int numberOfDay) {
        return ofNullable(nextCondition).isPresent() ?
                nextCondition.getWeather(galaxy, numberOfDay) :
                Weather.UNDEFINED;
    }
}
