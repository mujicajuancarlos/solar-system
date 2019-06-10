package ar.com.mercadolibre.solarsystem.service;

import ar.com.mercadolibre.solarsystem.entity.Weather;

public interface WeatherService {

    Weather findByDay(int day);
}
