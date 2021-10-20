package mx.restaurante.cv.restaurantapimng.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.restaurante.cv.restaurantapimng.models.entities.Venta;
import mx.restaurante.cv.restaurantapimng.models.repository.VendedorDao;
import mx.restaurante.cv.restaurantapimng.models.repository.VentaDao;
import mx.restaurante.to.InfoPedido;

@Service
public class VentaServiceImpl implements VentaServiceI {
  
  @Autowired
  VentaDao ventaDao;

  @Autowired
  VendedorDao vendedorDao;

  @Override
  public Venta save(InfoPedido infoPedido) {

    vendedorDao.findByNoEmpleado(Long.parseLong(infoPedido.getNoEmpleado()));

    Venta venta = new Venta();
    return ventaDao.save(venta);
  }

  @Override
  public List<Venta> findVentaByVendedor_noEmpleado(Long noEmpleado) {
    return ventaDao.findVentaByVendedor_noEmpleado(noEmpleado);
  }
}
