package mx.restaurante.cv.restaurantapimng.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import mx.restaurante.cv.restaurantapimng.models.entities.Platillo;

public interface PlatilloDao extends PagingAndSortingRepository<Platillo, Long> {
  
}
