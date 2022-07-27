package mx.restaurante.cv.restaurantapimng.services;

import java.util.List;
import java.util.Optional;

import mx.restaurante.cv.restaurantapimng.aggregate.VentaAbs;
import mx.restaurante.cv.restaurantapimng.models.entities.Platillo;
import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;
import mx.restaurante.cv.restaurantapimng.models.entities.Venta;
import mx.restaurante.to.InfoPedido;
import mx.restaurante.to.InfoVenta;
import mx.restaurante.to.PedidoTO;
import org.springframework.transaction.annotation.Transactional;

public interface VendedorServiceI {
  
  public List<Vendedor> findAll();

  Optional<Vendedor> findVendedorById(Long id);

  void deleteVendedorById(Long id);

  List<Platillo> findAllPlatillos();

  public Platillo findPlatilloById(Long id);

  @Transactional(readOnly = true)
  Optional<Venta> findVentaById(Long id);

  public Venta save(InfoPedido infoPedido);

  Venta agregarPlatillo(PedidoTO pedido);

  @Transactional
  Venta cerrarVenta(InfoVenta infoVenta);

  public List<Venta> findVentaByVendedor_noEmpleado(Long noEmpleado);

  public Venta fetchIdWithVendedorWithDetalleVentaWithPlatillo(Long id);

  public Vendedor save(Vendedor vendedor);

}