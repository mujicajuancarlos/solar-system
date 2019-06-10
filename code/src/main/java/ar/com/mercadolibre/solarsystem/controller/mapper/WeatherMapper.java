package ar.com.mercadolibre.solarsystem.controller.mapper;

import ar.com.mercadolibre.solarsystem.dto.WeatherDTO;
import ar.com.mercadolibre.solarsystem.entity.Weather;
import org.mapstruct.Mapper;

@Mapper
public interface WeatherMapper {

    WeatherDTO toDTO(Weather weather);
}
