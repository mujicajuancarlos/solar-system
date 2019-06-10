package ar.com.mercadolibre.solarsystem.service;

import ar.com.mercadolibre.solarsystem.model.WeatherStatistics;

public interface MeteorologicService {

    /**
     * Funcionalidad que evalua cada uno de los dias y acumula los resultados en @{@link WeatherStatistics}
     *
     * @param years representa la cantidad de años que se quiere calcular
     * @return intancia de @{@link WeatherStatistics} con las estadisticas meteorologicas
     */
    WeatherStatistics calculateWeatherStatistics(int years);
}
