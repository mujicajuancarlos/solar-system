package ar.com.mercadolibre.solarsystem.service;

import ar.com.mercadolibre.solarsystem.model.WeatherStatistics;
import ar.com.mercadolibre.solarsystem.model.WeatherType;

public interface MeteorologicService {

    /**
     * Funcionalidad que evalua cada uno de los dias y acumula los resultados en @{@link WeatherStatistics}
     *
     * @param years representa la cantidad de años que se quiere calcular
     * @return intancia de @{@link WeatherStatistics} con las estadisticas meteorologicas
     */
    WeatherStatistics calculateWeatherStatistics(int years);

    /**
     * Funcionalidad que calcula el tipo de clima para un dia especifico
     *
     * @param day día especifico a calcular
     * @return @{@link WeatherType}
     */
    WeatherType calculateWeather(int day);
}
