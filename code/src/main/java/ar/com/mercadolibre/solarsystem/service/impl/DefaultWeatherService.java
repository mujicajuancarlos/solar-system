package ar.com.mercadolibre.solarsystem.service.impl;

import ar.com.mercadolibre.solarsystem.entity.Weather;
import ar.com.mercadolibre.solarsystem.exception.EntityNotFoundException;
import ar.com.mercadolibre.solarsystem.model.WeatherType;
import ar.com.mercadolibre.solarsystem.repository.WeatherRepository;
import ar.com.mercadolibre.solarsystem.service.MeteorologicService;
import ar.com.mercadolibre.solarsystem.service.WeatherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.IntStream;

import static ar.com.mercadolibre.solarsystem.utils.DateUtils.getDaysBetween;

@Service
public class DefaultWeatherService implements WeatherService {

    private static final Logger LOGGER = LogManager.getLogger(MeteorologicService.class);

    @Autowired
    private MeteorologicService meteorologicService;

    @Autowired
    private WeatherRepository weatherRepository;

    @Value("#{new java.text.SimpleDateFormat('${solar-system.date-format}').parse('${solar-system.start-date}')}")
    private Date startDate;

    /**
     * {@inheritDoc}
     *
     * @see @{@link WeatherService#findByDay(Integer)}
     */
    @Override
    public Weather findByDay(Integer day) {
        return weatherRepository.findByDay(day)
                .orElseThrow(() -> new EntityNotFoundException("El clima para el día %s no existe.", day));
    }

    /**
     * {@inheritDoc}
     *
     * @see @{@link WeatherService#registryWeather(Date)}
     */
    @Override
    public void registryWeather(Date endDate) {
        int lastDate = getDaysBetween(startDate, endDate);
        IntStream.range(0, lastDate + 1)
                .parallel()
                .forEach(day -> {
                    if (!weatherRepository.findByDay(day).isPresent()) {
                        WeatherType type = meteorologicService.calculateWeather(day);
                        createWeather(new Weather(day, type.getDescription()));
                    }
                });
    }

    /**
     * {@inheritDoc}
     *
     * @see @{@link WeatherService#createWeather(Weather)}
     */
    @Override
    public Weather createWeather(Weather weather) {
        LOGGER.info("Inserting weather " + weather.toString());
        return weatherRepository.save(weather);
    }
}
