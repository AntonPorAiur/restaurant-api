package mx.restaurante.cv.restaurantapimng.services;

import java.util.List;
import java.util.Optional;

import mx.restaurante.cv.restaurantapimng.models.entities.Platillo;
import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;
import mx.restaurante.cv.restaurantapimng.models.entities.Venta;
import mx.restaurante.to.InfoPedido;
import org.springframework.transaction.annotation.Transactional;

public interface VendedorServiceI {
  
  public List<Vendedor> findAll();

  Optional<Vendedor> findVendedorById(Long id);

  void deleteVendedorById(Long id);

  List<Platillo> findAllPlatillos();

  public Platillo findPlatilloById(Long id);

  public Venta save(InfoPedido infoPedido);

  public List<Venta> findVentaByVendedor_noEmpleado(Long noEmpleado);

  public Venta fetchIdWithVendedorWithDetalleVentaWithPlatillo(Long id);

  public Vendedor save(Vendedor vendedor);

}