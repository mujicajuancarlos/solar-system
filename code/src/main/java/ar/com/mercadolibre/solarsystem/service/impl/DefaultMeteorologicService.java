package ar.com.mercadolibre.solarsystem.service.impl;

import ar.com.mercadolibre.solarsystem.core.WeatherConditionChain;
import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.model.WeatherStatistics;
import ar.com.mercadolibre.solarsystem.model.WeatherType;
import ar.com.mercadolibre.solarsystem.service.MeteorologicService;
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

import static ar.com.mercadolibre.solarsystem.utils.DateUtils.getDaysFrom;
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
                .range(0, getDaysFrom(startDate, years))
                .forEach(day -> evaluateDayWeather(statistics, day));
        return statistics;
    }

    /**
     * {@inheritDoc}
     *
     * @see @{@link MeteorologicService#calculateWeather(int)}
     */
    @Override
    public WeatherType calculateWeather(int day) {
        return weatherCondition.getWeather(galaxy, day);
    }

    private void evaluateDayWeather(WeatherStatistics statistics, int day) {
        WeatherType weatherType = weatherCondition.getWeather(galaxy, day);
        statistics.increment(weatherType);
        if (weatherType.isRain()) {
            List<Point> points = galaxy.getCoordinatesForDay(day);
            statistics.updateMaximumRainDay(day, perimeter(points));
        }
    }
}
