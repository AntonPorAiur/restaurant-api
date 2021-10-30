package mx.restaurante.cv.restaurantapimng.services;

import java.util.List;
import java.util.Optional;

import mx.restaurante.cv.restaurantapimng.models.entities.DetalleVenta;
import mx.restaurante.cv.restaurantapimng.models.entities.Platillo;
import mx.restaurante.cv.restaurantapimng.models.entities.Venta;
import mx.restaurante.cv.restaurantapimng.models.repository.PlatilloDao;
import mx.restaurante.cv.restaurantapimng.models.repository.VentaDao;
import mx.restaurante.to.InfoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;
import mx.restaurante.cv.restaurantapimng.models.repository.VendedorDao;

@Service
public class VendedorServiceImpl implements VendedorServiceI {

  @Autowired
  VendedorDao vendedorDao;

  @Autowired
  PlatilloDao platilloDao;

  @Autowired
  VentaDao ventaDao;

  @Override
  @Transactional(readOnly = true)
  public List<Vendedor> findAll() {
    return (List<Vendedor>) vendedorDao.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Vendedor> findVendedorById(Long id) {
    Optional<Vendedor> vendedor = vendedorDao.findById(id);
    return vendedor;
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteVendedorById(Long id) {
    vendedorDao.deleteById(id);
  }

  @Override
  @Transactional(readOnly = false)
  public Vendedor save(Vendedor vendedor) {
    return vendedorDao.save(vendedor);
  }


  @Override
  @Transactional(readOnly = true)
  public List<Platillo> findAllPlatillos() {
    return (List<Platillo>) platilloDao.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Platillo findPlatilloById(Long id) {
    return platilloDao.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public Venta save(InfoPedido infoPedido) {

    Vendedor vendedor = vendedorDao.findByNoEmpleado(infoPedido.getNoEmpleado());

    Venta venta = new Venta();
    venta.setVendedor(vendedor);
    venta.setObservacion(infoPedido.getObservacion());
    venta.setPropina(infoPedido.getPropina());

    for(int i = 0; i < infoPedido.getPedido().size(); i++) {

      Platillo platillo = platilloDao.findById(infoPedido.getPedido().get(i).getId()).orElse(null);

      DetalleVenta detalleVenta = new DetalleVenta();
      detalleVenta.setPlatillo(platillo);
      detalleVenta.setCantidad(infoPedido.getPedido().get(i).getCantidad());
      venta.addDetalleVenta(detalleVenta);

    }

    return ventaDao.save(venta);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Venta> findVentaByVendedor_noEmpleado(Long noEmpleado) {
    return ventaDao.findVentaByVendedor_noEmpleado(noEmpleado);
  }

  @Override
  @Transactional(readOnly = true)
  public Venta fetchIdWithVendedorWithDetalleVentaWithPlatillo(Long id) {
    return  ventaDao.fetchByIdWithVendedorWithDetalleVentaWithPlatillo(id);
  }


}
