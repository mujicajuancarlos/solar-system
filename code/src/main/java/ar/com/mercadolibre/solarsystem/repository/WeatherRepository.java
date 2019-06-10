package ar.com.mercadolibre.solarsystem.repository;

import ar.com.mercadolibre.solarsystem.entity.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {

    Optional<Weather> findByDay(Integer day);

}
