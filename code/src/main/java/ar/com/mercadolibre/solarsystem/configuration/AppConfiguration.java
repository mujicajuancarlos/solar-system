package ar.com.mercadolibre.solarsystem.configuration;

import ar.com.mercadolibre.solarsystem.model.Galaxy;
import ar.com.mercadolibre.solarsystem.provider.PlanetsProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(AppConfiguration.class);

    @Autowired
    private PlanetsProvider planetsProvider;

    @Bean
    @Qualifier("galaxy")
    public Galaxy getGalaxy() {
        Galaxy galaxy = new Galaxy();
        galaxy.setPlanets(planetsProvider.getPlanets());
        return galaxy;
    }
}
