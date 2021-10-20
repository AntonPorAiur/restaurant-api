package mx.restaurante.cv.restaurantapimng.models.repository;

import org.springframework.data.repository.CrudRepository;

import mx.restaurante.cv.restaurantapimng.models.entities.Platillo;

public interface PlatilloDao extends CrudRepository<Platillo, Long> {
  
}
