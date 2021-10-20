package mx.restaurante.cv.restaurantapimng.services;

import java.util.List;
import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;

public interface VendedorServiceI {
  
  public List<Vendedor> findAll();

}