package ar.com.mercadolibre.solarsystem.controller.mapper;

import ar.com.mercadolibre.solarsystem.dto.WeatherDTO;
import ar.com.mercadolibre.solarsystem.dto.WeatherStatisticsDTO;
import ar.com.mercadolibre.solarsystem.entity.Weather;
import ar.com.mercadolibre.solarsystem.model.WeatherStatistics;
import org.mapstruct.Mapper;

@Mapper
public interface WeatherMapper {

    WeatherDTO toDTO(Weather weather);

    WeatherStatisticsDTO toDTO(WeatherStatistics statistics);
}
