package ar.com.mercadolibre.solarsystem.service.impl;

import ar.com.mercadolibre.solarsystem.core.WeatherConditionChain;
import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.Weather;
import ar.com.mercadolibre.solarsystem.model.WeatherStatistics;
import ar.com.mercadolibre.solarsystem.service.MeteorologicService;
import ar.com.mercadolibre.solarsystem.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static ar.com.mercadolibre.solarsystem.utils.PointUtils.perimeter;

@Service
public class DefaultMeteorologicService implements MeteorologicService {

    private static final Logger LOGGER = LogManager.getLogger(MeteorologicService.class);

    @Autowired
    @Qualifier("DroughtCondition")
    private WeatherConditionChain weatherCondition;

    @Autowired
    private Galaxy galaxy;

    @Value("#{new java.text.SimpleDateFormat('${solar-system.date-format}').parse('${solar-system.start-date}')}")
    private Date startDate;

    @PostConstruct
    private void init() {
        LOGGER.info(weatherCondition + " assigned.");
        LOGGER.info(galaxy + " assigned.");
    }

    /**
     * {@inheritDoc}
     *
     * @see @{@link MeteorologicService#calculateWeatherStatistics(int)}
     */
    @Override
    public WeatherStatistics calculateWeatherStatistics(int years) {
        WeatherStatistics statistics = new WeatherStatistics();
        IntStream
                .range(0, DateUtils.getDaysFrom(startDate, years))
                .forEach(value -> evaluateDayWeather(statistics, value));
        return statistics;
    }

    private void evaluateDayWeather(WeatherStatistics statistics, int value) {
        Weather weather = weatherCondition.getWeather(galaxy, value);
        statistics.increment(weather);
        if (weather.isRain()) {
            List<Point> points = galaxy.getCoordinatesForDay(value);
            statistics.updateMaximumRainDay(value, perimeter(points));
        }
    }
}
