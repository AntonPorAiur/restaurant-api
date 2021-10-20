package mx.restaurante.cv.restaurantapimng.models.repository;

import org.springframework.data.repository.CrudRepository;

import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;

public interface VendedorDao extends CrudRepository<Vendedor, Long> {

  public Vendedor findByNoEmpleado(Long noEmpleado);
    
}
