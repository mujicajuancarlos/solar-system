package ar.com.mercadolibre.solarsystem.configuration;

import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.provider.PlanetsProvider;
import ar.com.mercadolibre.solarsystem.service.WeatherService;
import ar.com.mercadolibre.solarsystem.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Date;

@Configuration
public class AppConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(AppConfiguration.class);

    @Autowired
    private PlanetsProvider planetsProvider;
    @Autowired
    private WeatherService weatherService;

    @Bean
    @Qualifier("galaxy")
    public Galaxy getGalaxy() {
        Galaxy galaxy = new Galaxy();
        galaxy.setPlanets(planetsProvider.getPlanets());
        return galaxy;
    }

    @PostConstruct
    private void init() {
        LOGGER.info("Estadisticas para los proximos 10 años:");
        Date endDate = DateUtils.plusYears(new Date(), 10);
        weatherService.registryWeather(endDate);
    }
}
