package mx.restaurante.cv.restaurantapimng.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import mx.restaurante.cv.restaurantapimng.models.entities.Venta;

public interface VentaDao extends CrudRepository<Venta, Long> {
 
  public List<Venta> findVentaByVendedor_noEmpleado(Long noEmpleado);

}
