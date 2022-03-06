package org.reactive.curd;

import org.reactive.model.City;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-03-06 15:01
 */
public interface CityMapper extends CrudRepository<City, Integer> {

    Optional<City> findByName(String name);
}