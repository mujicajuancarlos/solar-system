package ar.com.mercadolibre.solarsystem.service;

import ar.com.mercadolibre.solarsystem.entity.Weather;

import java.util.Date;

public interface WeatherService {

    /**
     * Obtiene el clima para un dia determinado
     *
     * @param day
     * @return @{@link Weather}
     */
    Weather findByDay(Integer day);

    /**
     * Calcula y registra el clima hasta la fecha  #endDate
     *
     * @param endDate @{@link Date}
     */
    void registryWeather(Date endDate);

    /**
     * Guarda el objeto en la base de datos
     *
     * @param weather @{@link Weather}
     */
    Weather createWeather(Weather weather);
}
