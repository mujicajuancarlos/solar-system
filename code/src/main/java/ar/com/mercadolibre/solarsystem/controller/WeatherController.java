package ar.com.mercadolibre.solarsystem.controller;

import ar.com.mercadolibre.solarsystem.controller.mapper.WeatherMapper;
import ar.com.mercadolibre.solarsystem.dto.WeatherDTO;
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
    private WeatherMapper weatherMapper;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
        this.weatherMapper = Mappers.getMapper(WeatherMapper.class);
    }

    @GetMapping
    public ResponseEntity<WeatherDTO> getWeather(@Valid @RequestParam("dia") int day) {
        return new ResponseEntity(
                weatherMapper.toDTO(
                        weatherService.findByDay(day)),
                OK);
    }
}
