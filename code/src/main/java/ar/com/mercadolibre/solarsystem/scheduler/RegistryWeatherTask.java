package ar.com.mercadolibre.solarsystem.scheduler;

import ar.com.mercadolibre.solarsystem.service.WeatherService;
import ar.com.mercadolibre.solarsystem.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class RegistryWeatherTask {

    private static final Logger logger = LoggerFactory.getLogger(RegistryWeatherTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private WeatherService weatherService;

    //@Scheduled(cron = "${solar-system.cron-expression}")
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void registryNextWeather() {
        LocalDateTime date = LocalDateTime.now();
        Date endDate = DateUtils.plusYears(new Date(), 10);
        logger.info("Start task :: Time - {}", dateTimeFormatter.format(date));
        weatherService.registryWeather(endDate);
        logger.info("End task :: Time - {}", dateTimeFormatter.format(date));
    }

}
