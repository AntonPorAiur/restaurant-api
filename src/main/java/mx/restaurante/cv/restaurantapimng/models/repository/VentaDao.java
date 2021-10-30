package mx.restaurante.cv.restaurantapimng.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import mx.restaurante.cv.restaurantapimng.models.entities.Venta;

public interface VentaDao extends CrudRepository<Venta, Long> {
 
  public List<Venta> findVentaByVendedor_noEmpleado(Long noEmpleado);

  @Query("SELECT v FROM Venta v JOIN FETCH v.vendedor m JOIN FETCH v.detalle d JOIN d.platillo WHERE v.id=?1")
  public Venta fetchByIdWithVendedorWithDetalleVentaWithPlatillo(Long id);

}