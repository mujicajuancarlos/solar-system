package ar.com.mercadolibre.solarsystem.provider;

import ar.com.mercadolibre.solarsystem.model.Planet;

import java.util.List;

/**
 * Facilita la inicializacion de los planetas que componen la galaxia
 */
public interface PlanetsProvider {

    /**
     * Retorna una lista de @{@link Planet}
     *
     * @return {@link java.util.List<Planet>}
     */
    List<Planet> getPlanets();
}
