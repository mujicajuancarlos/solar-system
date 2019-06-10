package ar.com.mercadolibre.solarsystem.provider.impl;

import ar.com.mercadolibre.solarsystem.core.YamlPropertyLoaderFactory;
import ar.com.mercadolibre.solarsystem.model.Planet;
import ar.com.mercadolibre.solarsystem.provider.PlanetsProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de configuración que busca los valores de menu-items dispoibles en el archivo:
 * menu.items.yml
 * Este archivo debe tener la siguiente distribución de información:
 * <p>
 * solar-system
 * *    planets:
 * *        - name: MENU_ID
 * *          radius: MENU_NAME
 * *          velocity: FRONTEND_URL
 */
@Component
@PropertySource(
        factory = YamlPropertyLoaderFactory.class,
        value = "classpath:planets.yml"
)
@ConfigurationProperties(prefix = "galaxy")
public class YamlPlanetsProvider implements PlanetsProvider {

    private static final Logger LOGGER = LogManager.getLogger(PlanetsProvider.class);

    private List<Planet> planets = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    @PostConstruct
    private void init() {
        planets.forEach(planet -> LOGGER.info(planet.toString() + " created."));
    }
}
