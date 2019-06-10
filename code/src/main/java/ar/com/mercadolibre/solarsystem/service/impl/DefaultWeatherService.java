package ar.com.mercadolibre.solarsystem.service.impl;

import ar.com.mercadolibre.solarsystem.entity.Weather;
import ar.com.mercadolibre.solarsystem.service.WeatherService;
import org.springframework.stereotype.Service;

@Service
public class DefaultWeatherService implements WeatherService {

    @Override
    public Weather findByDay(int day) {
        return new Weather();
    }
}
