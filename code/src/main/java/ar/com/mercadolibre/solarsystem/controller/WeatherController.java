package ar.com.mercadolibre.solarsystem.controller;

import ar.com.mercadolibre.solarsystem.controller.mapper.WeatherMapper;
import ar.com.mercadolibre.solarsystem.dto.WeatherDTO;
import ar.com.mercadolibre.solarsystem.dto.WeatherStatisticsDTO;
import ar.com.mercadolibre.solarsystem.service.MeteorologicService;
import ar.com.mercadolibre.solarsystem.service.WeatherService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping(path = "/clima")
@RestController
public class WeatherController {

    private WeatherService weatherService;
    private MeteorologicService meteorologicService;
    private WeatherMapper weatherMapper;

    @Autowired
    public WeatherController(WeatherService weatherService, MeteorologicService meteorologicService) {
        this.weatherService = weatherService;
        this.meteorologicService = meteorologicService;
        this.weatherMapper = Mappers.getMapper(WeatherMapper.class);
    }

    @GetMapping
    public ResponseEntity<WeatherDTO> getWeather(@Valid @RequestParam("dia") int day) {
        return new ResponseEntity(
                weatherMapper.toDTO(
                        weatherService.findByDay(day)),
                OK);
    }

    @GetMapping("/reporte")
    public ResponseEntity<WeatherStatisticsDTO> getWeatherStatistics() {
        return new ResponseEntity(
                weatherMapper.toDTO(
                        meteorologicService.calculateWeatherStatistics(10)),
                OK);
    }
}
