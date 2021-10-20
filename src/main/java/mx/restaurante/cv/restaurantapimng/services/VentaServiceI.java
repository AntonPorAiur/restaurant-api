package mx.restaurante.cv.restaurantapimng.services;

import java.util.List;
import mx.restaurante.cv.restaurantapimng.models.entities.Venta;
import mx.restaurante.to.InfoPedido;

public interface VentaServiceI {

  public Venta save(InfoPedido infoPedido);

  public List<Venta> findVentaByVendedor_noEmpleado(Long noEmpleado);

}
